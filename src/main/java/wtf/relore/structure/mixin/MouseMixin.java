package wtf.relore.structure.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import net.minecraft.client.input.MouseButtonInfo;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wtf.relore.structure.utils.input.KeyBoardUtil;

@Mixin(MouseHandler.class)
public abstract class MouseMixin {

   @Shadow
   @Final
   private Minecraft minecraft;

   @Inject(method = "onButton", at = @At("HEAD"), cancellable = false)
   private void onMouseButton(long window, MouseButtonInfo buttonInfo, int action, CallbackInfo ci) {
      try {
         if (this.minecraft.player == null) return;

         int buttonId = buttonInfo.button();
         int actionId = action == GLFW.GLFW_PRESS ? 1 : 0;
         KeyBoardUtil.callMouse(buttonId, actionId);

      } catch (Exception e) {
      }
   }
}
