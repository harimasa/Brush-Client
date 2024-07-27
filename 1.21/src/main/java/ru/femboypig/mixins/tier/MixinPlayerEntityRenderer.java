package ru.femboypig.mixins.tier;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import ru.femboypig.config.BrushCC;
import ru.femboypig.utils.HPUtils;
import ru.femboypig.utils.TierUtils;

import static ru.femboypig.utils.interfaces.Instance.mc;


@Mixin(PlayerEntityRenderer.class)
public class MixinPlayerEntityRenderer {
    private static LivingEntity entity;

    @WrapOperation(method = "renderLabelIfPresent(Lnet/minecraft/client/network/AbstractClientPlayerEntity;Lnet/minecraft/text/Text;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/LivingEntityRenderer;renderLabelIfPresent(Lnet/minecraft/entity/Entity;Lnet/minecraft/text/Text;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IF)V", ordinal = 1))
    public void addTierToNametag(PlayerEntityRenderer instance, Entity entity, Text text, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, float v, Operation<Void> original) {
        String currentPlayerName = mc.player.getName().getString();
        if (entity instanceof PlayerEntity && entity.getName().getString().equals(currentPlayerName) && BrushCC.CONFIG.instance().fakeTier && entity instanceof PlayerEntity player) {
            text = TierUtils.appendTier(player, text);
        }
        original.call(instance, entity, text, matrixStack, vertexConsumerProvider, i, v);
    }

    @WrapOperation(method = "renderLabelIfPresent(Lnet/minecraft/client/network/AbstractClientPlayerEntity;Lnet/minecraft/text/Text;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/LivingEntityRenderer;renderLabelIfPresent(Lnet/minecraft/entity/Entity;Lnet/minecraft/text/Text;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IF)V", ordinal = 1))
    public void addHPToNametag(PlayerEntityRenderer instance, Entity entity, Text text, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, float v, Operation<Void> original) {
        String currentPlayerName = mc.player.getName().getString();
        if (entity instanceof PlayerEntity && !entity.getName().getString().equals(currentPlayerName) && BrushCC.CONFIG.instance().hp && entity instanceof PlayerEntity player) {
            text = HPUtils.appendHP(player, text);
        }
        original.call(instance, entity, text, matrixStack, vertexConsumerProvider, i, v);
    }
}