package wtf.relore.structure.utils.render;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;

import java.util.ArrayDeque;
import java.util.Deque;

public final class RenderUtil {
    private static final ThreadLocal<GuiGraphicsExtractor> ACTIVE_CONTEXT = new ThreadLocal<>();
    private static final Deque<Rect> SCISSORS = new ArrayDeque<>();

    private RenderUtil() {
    }

    public static void beginFrame(GuiGraphicsExtractor context) {
        ACTIVE_CONTEXT.set(context);
    }

    public static void endFrame() {
        ACTIVE_CONTEXT.remove();
    }

    public static GuiGraphicsExtractor context() {
        GuiGraphicsExtractor context = ACTIVE_CONTEXT.get();
        if (context == null) {
            throw new IllegalStateException("RenderUtil has no active GUI context. Call from EventRender2D or pass GuiGraphicsExtractor directly.");
        }
        return context;
    }

    public static void flush(RenderPipeline pipeline) {
        // The Java port renders directly through GuiGraphicsExtractor, so there is no queued batch to flush.
    }

    public static void flushAll() {
    }

    public static void rect(float x, float y, float width, float height, int color) {
        rect(context(), x, y, width, height, color);
    }

    public static void rect(GuiGraphicsExtractor context, float x, float y, float width, float height, int color) {
        context.fill(round(x), round(y), round(x + width), round(y + height), color);
    }

    public static void gradientRect(float x, float y, float width, float height, int topLeft, int topRight, int bottomRight, int bottomLeft) {
        gradientRect(context(), x, y, width, height, topLeft, topRight, bottomRight, bottomLeft);
    }

    public static void gradientRect(GuiGraphicsExtractor context, float x, float y, float width, float height, int topLeft, int topRight, int bottomRight, int bottomLeft) {
        int steps = Math.max(1, round(height));
        for (int i = 0; i < steps; i++) {
            float progress = steps == 1 ? 0.0F : (float) i / (float) (steps - 1);
            int left = lerpColor(topLeft, bottomLeft, progress);
            int right = lerpColor(topRight, bottomRight, progress);
            int color = lerpColor(left, right, 0.5F);
            context.fill(round(x), round(y) + i, round(x + width), round(y) + i + 1, color);
        }
    }

    public static void roundedRect(float x, float y, float width, float height, float radius, int color) {
        roundedRect(context(), x, y, width, height, radius, color);
    }

    public static void roundedRect(GuiGraphicsExtractor context, float x, float y, float width, float height, float radius, int color) {
        int r = Math.max(0, Math.min(round(radius), Math.min(round(width), round(height)) / 2));
        int ix = round(x);
        int iy = round(y);
        int iw = round(width);
        int ih = round(height);

        context.fill(ix + r, iy, ix + iw - r, iy + ih, color);
        context.fill(ix, iy + r, ix + iw, iy + ih - r, color);

        for (int dy = 0; dy < r; dy++) {
            int inset = r - (int) Math.sqrt((double) r * r - (double) (r - dy) * (r - dy));
            context.fill(ix + inset, iy + dy, ix + iw - inset, iy + dy + 1, color);
            context.fill(ix + inset, iy + ih - dy - 1, ix + iw - inset, iy + ih - dy, color);
        }
    }

    public static void startScissor(float x, float y, float width, float height) {
        GuiGraphicsExtractor context = context();
        Rect rect = new Rect(round(x), round(y), round(width), round(height));
        SCISSORS.push(rect);
        context.enableScissor(rect.x(), rect.y(), rect.x() + rect.width(), rect.y() + rect.height());
    }

    public static void endScissor() {
        GuiGraphicsExtractor context = context();
        if (!SCISSORS.isEmpty()) {
            SCISSORS.pop();
        }
        if (SCISSORS.isEmpty()) {
            context.disableScissor();
            return;
        }
        Rect rect = SCISSORS.peek();
        context.enableScissor(rect.x(), rect.y(), rect.x() + rect.width(), rect.y() + rect.height());
    }

    public static void scissor(float x, float y, float width, float height, Runnable action) {
        startScissor(x, y, width, height);
        action.run();
        endScissor();
    }

    public static Identifier reloreTexture(String path) {
        return Identifier.fromNamespaceAndPath("relore", path);
    }

    private static int round(float value) {
        return Math.round(value);
    }

    private static int lerpColor(int from, int to, float progress) {
        int a = lerp((from >>> 24) & 255, (to >>> 24) & 255, progress);
        int r = lerp((from >>> 16) & 255, (to >>> 16) & 255, progress);
        int g = lerp((from >>> 8) & 255, (to >>> 8) & 255, progress);
        int b = lerp(from & 255, to & 255, progress);
        return (a << 24) | (r << 16) | (g << 8) | b;
    }

    private static int lerp(int from, int to, float progress) {
        return Math.round(from + (to - from) * progress);
    }

    private record Rect(int x, int y, int width, int height) {
    }
}
