package wtf.relore.structure.mixin;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wtf.relore.structure.utils.render.RenderEventDispatcher;

@Mixin(GuiGraphicsExtractor.class)
public class InGameHudMixin {
    @Inject(method = "extractDeferredElements", at = @At("HEAD"))
    private void relore$render2D(int mouseX, int mouseY, float tickDelta, CallbackInfo ci) {
        RenderEventDispatcher.dispatch2D((GuiGraphicsExtractor) (Object) this, tickDelta);
    }
}
