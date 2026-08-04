package wtf.relore.structure.utils.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderBuffers;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import wtf.relore.client.events.impl.render.EventRender2D;
import wtf.relore.client.events.impl.render.EventRender3D;
import wtf.relore.client.events.impl.render.EventRender3DEndMain;

public final class RenderEventDispatcher {
    private RenderEventDispatcher() {
    }

    public static void dispatch2D(GuiGraphicsExtractor context, float tickDelta) {
        RenderUtil.beginFrame(context);
        new EventRender2D(context, tickDelta).call();
        RenderUtil.flush(RenderPipeline.HUD);
        RenderUtil.endFrame();
    }

    public static void dispatch3D(CameraRenderState camera, DeltaTracker deltaTracker, RenderBuffers renderBuffers) {
        new EventRender3D(new PoseStack(), camera, deltaTracker.getGameTimeDeltaPartialTick(true)).call();
    }

    public static void dispatch3DEnd(CameraRenderState camera, DeltaTracker deltaTracker, RenderBuffers renderBuffers) {
        new EventRender3DEndMain(new PoseStack(), camera, deltaTracker.getGameTimeDeltaPartialTick(true)).call();
    }
}
