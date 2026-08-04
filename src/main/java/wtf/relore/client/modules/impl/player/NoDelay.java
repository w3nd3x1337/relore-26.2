package wtf.relore.client.modules.impl.player;

import org.lwjgl.glfw.GLFW;
import wtf.relore.client.events.EventHook;
import wtf.relore.client.events.impl.EventUpdate;
import wtf.relore.client.modules.Module;
import wtf.relore.structure.managers.GlobalManager;
import wtf.relore.structure.mixin.accesor.ILivingEntityAccesor;

public class NoDelay extends Module {
    public NoDelay() {
        super("NoDelay", GlobalManager.t("group.desc"), Group.PLAYER);
        setKey(GLFW.GLFW_KEY_X);
    }

    @EventHook
    public void onUpdate(final EventUpdate ignored) {
        if (mc.player == null || mc.level == null) return;
        ((ILivingEntityAccesor) mc.player).setJumpingCooldown(0);
    }
}
