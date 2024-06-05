package ru.femboypig.mixins.menu;

import net.minecraft.client.gui.LogoDrawer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.femboypig.config.BrushCC;

@Mixin(LogoDrawer.class)
public class MixinLogoDrawer {
    @Inject(method = "draw*", at = @At("HEAD"), cancellable = true)
    public void cancelDraw(CallbackInfo ci) {
        if (BrushCC.CONFIG.instance().bcm) {
            ci.cancel();
        }
    }
}