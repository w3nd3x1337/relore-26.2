package wtf.relore.client.events.impl.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import wtf.relore.client.events.Event;

public class EventRender3D extends Event {
    private final PoseStack matrices;
    private final CameraRenderState camera;
    private final float tickDelta;

    public EventRender3D(PoseStack matrices, CameraRenderState camera, float tickDelta) {
        this.matrices = matrices;
        this.camera = camera;
        this.tickDelta = tickDelta;
    }

    public PoseStack getMatrices() {
        return matrices;
    }

    public CameraRenderState getCamera() {
        return camera;
    }

    public float getTickDelta() {
        return tickDelta;
    }

}
