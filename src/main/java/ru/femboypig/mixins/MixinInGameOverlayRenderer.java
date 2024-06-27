package ru.femboypig.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.femboypig.config.BrushCC;

@Mixin(InGameOverlayRenderer.class)
public class MixinInGameOverlayRenderer {
    @Inject(method = "renderFireOverlay", at = @At("HEAD"))
    private static void offsetFire(MinecraftClient client, MatrixStack matrices, CallbackInfo ci) {
        if (BrushCC.CONFIG.instance().lowFireEnabled) {
            double offset = -0.3;
            if (client.player.isCreative() || client.player.hasStatusEffect(StatusEffects.FIRE_RESISTANCE)) {
                offset = -10;
            }
            matrices.translate(0, offset, 0);
        }
    }
}