package ru.femboypig.mixins.tier;

import net.minecraft.client.render.entity.PlayerEntityRenderer;
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
    @ModifyArgs(method = "renderLabelIfPresent(Lnet/minecraft/client/network/AbstractClientPlayerEntity;Lnet/minecraft/text/Text;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/LivingEntityRenderer;renderLabelIfPresent(Lnet/minecraft/entity/Entity;Lnet/minecraft/text/Text;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", ordinal = 1))
    public void addTierToNametag(Args args) {
        String currentPlayerName = mc.player.getName().getString();
        LivingEntity entity = (LivingEntity) args.get(0);
        if (entity instanceof PlayerEntity && entity.getName().getString().equals(currentPlayerName) && BrushCC.CONFIG.instance().fakeTier) {
            Text text = args.get(1);
            text = TierUtils.appendTier((PlayerEntity) entity, text);
            args.set(1, text);
        }
    }

    @ModifyArgs(method = "renderLabelIfPresent(Lnet/minecraft/client/network/AbstractClientPlayerEntity;Lnet/minecraft/text/Text;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/LivingEntityRenderer;renderLabelIfPresent(Lnet/minecraft/entity/Entity;Lnet/minecraft/text/Text;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", ordinal = 1))
    public void addHPToNametag(Args args) {
        String currentPlayerName = mc.player.getName().getString();
        LivingEntity entity = (LivingEntity) args.get(0);
        if (entity instanceof PlayerEntity && !entity.getName().getString().equals(currentPlayerName) && BrushCC.CONFIG.instance().hp) {
            Text text = args.get(1);
            text = HPUtils.appendHP((PlayerEntity) entity, text);
            args.set(1, text);
        }
    }
}