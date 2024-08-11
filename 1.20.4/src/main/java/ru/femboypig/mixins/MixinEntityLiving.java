package ru.femboypig.mixins;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.femboypig.config.BrushCC;

@Mixin(LivingEntity.class)
public class MixinEntityLiving {

    @Inject(method = {"getHandSwingDuration"}, at = {@At("HEAD")}, cancellable = true)
    private void getArmSwingAnimationEnd(final CallbackInfoReturnable<Integer> info) {
        if (BrushCC.CONFIG.instance().animationsEnabled) {
            if (BrushCC.CONFIG.instance().slowAnimations) {
                info.setReturnValue((int) BrushCC.CONFIG.instance().slowValue);
            }
        }
    }
}