package ru.femboypig.mixins;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.femboypig.config.BrushCC;

@Mixin(ArmorFeatureRenderer.class)
public class MixinArmorFeatureRenderer<T extends LivingEntity, A extends BipedEntityModel<T>> {

    @Inject(method = "renderArmor", at = @At("HEAD"), cancellable = true)
    private void onRenderArmor(MatrixStack matrices, VertexConsumerProvider vertexConsumers, T livingEntity, EquipmentSlot slot, int i, A bipedEntityModel, CallbackInfo ci) {
        if (BrushCC.CONFIG.instance().NAREnabled) {
            ci.cancel();
        }
    }
}