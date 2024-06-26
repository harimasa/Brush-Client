package ru.femboypig.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.femboypig.config.BrushCC;

@Mixin(net.minecraft.entity.projectile.PersistentProjectileEntity.class)
public abstract class PersistentProjectileEntity {
    @Shadow
    public abstract void setSound(SoundEvent sound);

    @Unique
    private ClientPlayerEntity clientPlayerEntity;

    @Inject(method = "onEntityHit", at = @At("HEAD"))
    private void onEntityHitHead(EntityHitResult entityHitResult, CallbackInfo ci) {
        if (BrushCC.CONFIG.instance().swh) {
            if (this.clientPlayerEntity == null) {
                this.clientPlayerEntity = MinecraftClient.getInstance().player;
            }
        }

        Entity target = entityHitResult.getEntity();

        if (target instanceof LivingEntity) {
            ProjectileEntity projectile = (ProjectileEntity) (Object) this;
            if (BrushCC.CONFIG.instance().swh) {
                if (this.clientPlayerEntity.equals(projectile.getOwner())) {
                    this.setSound(SoundEvents.ENTITY_ARROW_HIT_PLAYER);
                } else {
                    this.setSound(SoundEvents.ENTITY_ARROW_HIT_PLAYER);
                }
            }
        }
    }

    @Inject(method = "onEntityHit", at = @At("TAIL"))
    private void onEntityHitTail(EntityHitResult entityHitResult, CallbackInfo ci) {
        if (BrushCC.CONFIG.instance().swh) {
            this.setSound(SoundEvents.ENTITY_ARROW_HIT);
        }
    }
}