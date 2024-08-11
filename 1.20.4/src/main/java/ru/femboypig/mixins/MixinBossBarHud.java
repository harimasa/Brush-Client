package ru.femboypig.mixins;

import net.minecraft.client.gui.hud.BossBarHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.femboypig.config.BrushCC;

@Mixin(BossBarHud.class)
public abstract class MixinBossBarHud {
    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void onRender(CallbackInfo ci) {
        if (BrushCC.CONFIG.instance().overlays && BrushCC.CONFIG.instance().bossbarOverlay) {
            ci.cancel();
        }
    }
}