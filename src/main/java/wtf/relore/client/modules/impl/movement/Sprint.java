package wtf.relore.client.modules.impl.movement;

import org.lwjgl.glfw.GLFW;
import wtf.relore.client.events.EventHook;
import wtf.relore.client.events.impl.EventUpdate;
import wtf.relore.client.modules.Module;
import wtf.relore.structure.managers.GlobalManager;

public class Sprint extends Module {
    public Sprint() {
        super("Sprint", GlobalManager.t("sprint.desc"), Group.MOVEMENT);
        setKey(GLFW.GLFW_KEY_R);
    }

    @EventHook
    public void onUpdate(final EventUpdate ignored) {
        if (mc.player == null) return;
        if (mc.player.input.keyPresses.forward()) mc.player.setSprinting(true);
    }
}
