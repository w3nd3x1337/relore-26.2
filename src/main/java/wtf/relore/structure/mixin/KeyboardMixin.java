package wtf.relore.structure.mixin;

import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.input.KeyEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wtf.relore.structure.utils.input.KeyBoardUtil;
import wtf.relore.structure.utils.interfaces.IClient;

@Mixin(KeyboardHandler.class)
public class KeyboardMixin implements IClient {
    @Inject(method = "keyPress", at = @At("HEAD"), cancellable = true)
    public void onKey(long window, int action, KeyEvent keyEvent, CallbackInfo ci) {
        KeyBoardUtil.call(keyEvent.key(), action);
    }
}
