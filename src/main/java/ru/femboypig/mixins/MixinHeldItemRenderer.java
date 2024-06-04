package ru.femboypig.mixins;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.femboypig.config.BrushCC;
import ru.femboypig.utils.interfaces.instance;

@Mixin(HeldItemRenderer.class)
public abstract class MixinHeldItemRenderer implements instance {
    public Arm arm = Arm.RIGHT;

    @Inject(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/HeldItemRenderer;renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"))
    private void onRenderAnim(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (BrushCC.CONFIG.instance().animationsEnabled) {
            if (hand == Hand.MAIN_HAND) {
                matrices.scale(BrushCC.CONFIG.instance().scale, BrushCC.CONFIG.instance().scale, BrushCC.CONFIG.instance().scale);
                float n = -0.4F * MathHelper.sin(MathHelper.sqrt(swingProgress) * 3.1415927F);
                applyEquipOffset(matrices, arm, n);
                int i = arm == Arm.RIGHT ? 1 : -1;
                float f1 = MathHelper.sin(swingProgress * swingProgress * 3.1415927F);
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((float) i * (45.0F + f1 * -20.0F)));
                float g = MathHelper.sin(MathHelper.sqrt(swingProgress) * 3.1415927F);
                matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees((float) i * g * -20.0F));
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(g * 0.0F));
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((float) i * -45.0F));
                onUpdate();
            } else {
                matrices.translate(-BrushCC.CONFIG.instance().posX, BrushCC.CONFIG.instance().posY, BrushCC.CONFIG.instance().posZ);
                matrices.scale(BrushCC.CONFIG.instance().scaleoff, BrushCC.CONFIG.instance().scaleoff, BrushCC.CONFIG.instance().scaleoff);
            }
        }
    }
    @Inject(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/HeldItemRenderer;renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"), cancellable = true)
    private void onRenderItem(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (BrushCC.CONFIG.instance().affectItems) {
            if (hand == Hand.MAIN_HAND) {
                matrices.translate(BrushCC.CONFIG.instance().posX, BrushCC.CONFIG.instance().posY, BrushCC.CONFIG.instance().posZ);
                matrices.scale(BrushCC.CONFIG.instance().scale, BrushCC.CONFIG.instance().scale, BrushCC.CONFIG.instance().scale);
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(BrushCC.CONFIG.instance().rotMainX));
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(BrushCC.CONFIG.instance().rotMainY));
                matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(BrushCC.CONFIG.instance().rotMainZ));
            } else {
                matrices.translate(-BrushCC.CONFIG.instance().posX, BrushCC.CONFIG.instance().posY, BrushCC.CONFIG.instance().posZ);
                matrices.scale(BrushCC.CONFIG.instance().scaleoff, BrushCC.CONFIG.instance().scaleoff, BrushCC.CONFIG.instance().scaleoff);
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(BrushCC.CONFIG.instance().rotOffX));
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(BrushCC.CONFIG.instance().rotOffY));
                matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(BrushCC.CONFIG.instance().rotOffZ));
            }
        }
    }

    @Inject(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/HeldItemRenderer;renderArmHoldingItem(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IFFLnet/minecraft/util/Arm;)V"), cancellable = true)
    private void onRenderArm(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (BrushCC.CONFIG.instance().affectArm) {
            matrices.translate(BrushCC.CONFIG.instance().posArmX, BrushCC.CONFIG.instance().posArmY, BrushCC.CONFIG.instance().posArmZ);
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(BrushCC.CONFIG.instance().rotArmX));
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(BrushCC.CONFIG.instance().rotArmY));
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(BrushCC.CONFIG.instance().rotArmZ));
        }
    }

    private void applyEquipOffset(@NotNull MatrixStack matrices, Arm arm, float equipProgress) {
        int i = arm == Arm.RIGHT ? 1 : -1;
        matrices.translate((float) i * 0.56F, -0.52F + equipProgress * -0.6F, -0.72F);
    }

    public void onUpdate() {
        if (BrushCC.CONFIG.instance().animationsEnabled && BrushCC.CONFIG.instance().animationsMain && ((IHeldItemRenderer) mc.getEntityRenderDispatcher().getHeldItemRenderer()).getEquippedProgressMainHand() <= 1f) {
            ((IHeldItemRenderer) mc.getEntityRenderDispatcher().getHeldItemRenderer()).setEquippedProgressMainHand(1f);
            ((IHeldItemRenderer) mc.getEntityRenderDispatcher().getHeldItemRenderer()).setItemStackMainHand(mc.player.getMainHandStack());
        }

        if (BrushCC.CONFIG.instance().animationsEnabled && BrushCC.CONFIG.instance().animationsOff && ((IHeldItemRenderer) mc.getEntityRenderDispatcher().getHeldItemRenderer()).getEquippedProgressOffHand() <= 1f) {
            ((IHeldItemRenderer) mc.getEntityRenderDispatcher().getHeldItemRenderer()).setEquippedProgressOffHand(1f);
            ((IHeldItemRenderer) mc.getEntityRenderDispatcher().getHeldItemRenderer()).setItemStackOffHand(mc.player.getOffHandStack());
        }
    }
}