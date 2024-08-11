package ru.femboypig.mixins.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.femboypig.config.BrushCC;

@Mixin(BipedEntityModel.class)
public class MixinBipedEntityModel {

    @Shadow
    public ModelPart rightArm;

    @Shadow
    public ModelPart leftArm;

    @Shadow
    public ModelPart head;

    @Shadow
    public ModelPart rightLeg;

    @Shadow
    public ModelPart leftLeg;

    @Inject(method = "setAngles", at = @At(value = "FIELD", target = "Lnet/minecraft/client/model/ModelPart;roll:F", ordinal = 1, shift = At.Shift.AFTER))
    private void setAngles(LivingEntity livingEntity, float f, float g, float h, float i, float j, CallbackInfo ci) {
        if (BrushCC.CONFIG.instance().badmodel) {
            if (livingEntity instanceof PlayerEntity) {
                // Hide Arm
                this.rightArm.visible = false;
                this.leftArm.visible = false;
                // Size head
                this.head.xScale = 1.5f;
                this.head.yScale = 1.5f;
                this.head.zScale = 1.5f;
                // Pos at x
                this.rightLeg.pivotX = 5f;
                this.leftLeg.pivotX = -5f;
            }
        }
    }
}