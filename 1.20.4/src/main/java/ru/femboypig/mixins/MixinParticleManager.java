package ru.femboypig.mixins;

import net.minecraft.client.particle.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.femboypig.config.BrushCC;

@Mixin(ParticleManager.class)
public class MixinParticleManager {
    @Inject(at = @At("HEAD"), method = "addParticle(Lnet/minecraft/client/particle/Particle;)V", cancellable = true)
    public void offParticles(Particle particle, CallbackInfo ci) {
        if (BrushCC.CONFIG.instance().noExplosions && particle instanceof ExplosionLargeParticle)
            ci.cancel();

        if (BrushCC.CONFIG.instance().noExplosions && particle instanceof CampfireSmokeParticle)
            ci.cancel();

        if (BrushCC.CONFIG.instance().noBreakPart && particle instanceof BlockDustParticle)
            ci.cancel();
    }
}