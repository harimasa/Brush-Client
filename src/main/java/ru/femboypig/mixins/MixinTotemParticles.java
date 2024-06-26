package ru.femboypig.mixins;

import net.minecraft.client.particle.AnimatedParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.particle.TotemParticle;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.femboypig.config.BrushCC;

@Mixin(TotemParticle.class)
public class MixinTotemParticles extends AnimatedParticle {
    protected MixinTotemParticles(ClientWorld world, double x, double y, double z, SpriteProvider spriteProvider, float upwardsAcceleration) {
        super(world, x, y, z, spriteProvider, upwardsAcceleration);
    }

    @Inject(method = "<init>(Lnet/minecraft/client/world/ClientWorld;DDDDDDLnet/minecraft/client/particle/SpriteProvider;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/TotemParticle;setSpriteForAge(Lnet/minecraft/client/particle/SpriteProvider;)V"))
    private void removeTotemParticleAge(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider, CallbackInfo ci) {
        if (BrushCC.CONFIG.instance().nototempart) {
            this.scale = 0;
        }
    }
}