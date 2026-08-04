package wtf.relore.client.events.impl.render;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import wtf.relore.client.events.Event;

public class EventRender2D extends Event {
    private final GuiGraphicsExtractor context;
    private final float tickDelta;

    public EventRender2D(GuiGraphicsExtractor context, float tickDelta) {
        this.context = context;
        this.tickDelta = tickDelta;
    }

    public GuiGraphicsExtractor getContext() {
        return context;
    }

    public float getTickDelta() {
        return tickDelta;
    }
}
