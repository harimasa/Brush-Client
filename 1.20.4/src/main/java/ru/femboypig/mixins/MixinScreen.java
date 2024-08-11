package ru.femboypig.mixins;

import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.femboypig.config.BrushCC;

@Mixin(Screen.class)
public abstract class MixinScreen {
    @Inject(method = "renderBackground", at = @At("HEAD"), cancellable = true)
    private void noGuiBackground(CallbackInfo ci) {
        if (BrushCC.CONFIG.instance().overlays && BrushCC.CONFIG.instance().guiOverlay) {
            ci.cancel();
        }
    }
}