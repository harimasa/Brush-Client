package ru.femboypig.mixins;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.InvalidIdentifierException;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.femboypig.config.BrushCC;

import java.util.ArrayList;
import java.util.List;

import static ru.femboypig.utils.interfaces.Instance.mc;

@Mixin(FlyingItemEntityRenderer.class)
public class MixinFlyingItemEntityRenderer<T extends Entity> {
    @Mutable
    @Final
    @Shadow private float scale;

    @Unique
    public List<Entity> notified = new ArrayList<>();

    @Inject(method = "render", at = @At("TAIL"))
    public void updated(T entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (BrushCC.CONFIG.instance().pearl && entity instanceof EnderPearlEntity) {
            this.scale = BrushCC.CONFIG.instance().pearlSize;
            if (entity.age <= 2 && !notified.contains(entity)) {
                if (BrushCC.CONFIG.instance().pearlSound) {
                    try {
                        Identifier soundId = new Identifier(BrushCC.CONFIG.instance().pearlSoundId);
                        mc.player.playSound(SoundEvent.of(soundId), 1, 1);
                    } catch (InvalidIdentifierException exception) {
                        mc.player.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                    }
                }
                notified.add(entity);
            }
        } else {
            this.scale = 1;
        }
    }
}