package ru.femboypig.mixins;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.femboypig.config.BrushCC;

@Mixin(ArmorFeatureRenderer.class)
public abstract class ArmorFeatureRendererMixin<T extends LivingEntity, M extends BipedEntityModel<T>, A extends BipedEntityModel<T>> extends FeatureRenderer<T, M> {

    @Unique
    private final float REDUCTION_FACTOR = 10f;

    @Unique
    private T entity;

    public ArmorFeatureRendererMixin(FeatureRendererContext<T, M> context) {
        super(context);
    }

    @Inject(method = "renderArmor", at = @At("HEAD"))
    private void hookRenderArmor(MatrixStack matrices, VertexConsumerProvider vertexConsumers, T entity, EquipmentSlot armorSlot, int light, A model, CallbackInfo ci) {
        this.entity = entity;
    }

    @ModifyVariable(method = "renderArmorParts", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private float hookRenderArmorPartsRed(float red) {
        if (BrushCC.CONFIG.instance().hitArmor) {
            if (entity.hurtTime >= 0) {
                return red;
            }
            return 255 + red + ((255 - REDUCTION_FACTOR) * getDamageFactor());
        }
        return red;
    }

    @ModifyVariable(method = "renderArmorParts", at = @At("HEAD"), ordinal = 1, argsOnly = true)
    private float hookRenderArmorPartsGreen(float green) {
        if (BrushCC.CONFIG.instance().hitArmor) {
            if (entity.hurtTime == 0) {
                return green;
            }
            return 1 + green + ((1 - REDUCTION_FACTOR) * getDamageFactor());
        }
        return green;
    }

    @ModifyVariable(method = "renderArmorParts", at = @At("HEAD"), ordinal = 2, argsOnly = true)
    private float hookRenderArmorPartsBlue(float blue) {
        if (BrushCC.CONFIG.instance().hitArmor) {
            if (entity.hurtTime == 0) {
                return blue;
            }
            return 1 + blue + ((1 - REDUCTION_FACTOR) * getDamageFactor());
        }
        return blue;
    }

    @Unique
    private float getDamageFactor() {
        return entity.hurtTime / (float) entity.maxHurtTime;
    }
}