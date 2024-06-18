package ru.femboypig.mixins.tier;

import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import ru.femboypig.config.BrushCC;
import ru.femboypig.utils.TierUtils;


@Mixin(PlayerEntityRenderer.class)
public class MixinPlayerEntityRenderer {
    @ModifyArgs(method = "renderLabelIfPresent(Lnet/minecraft/client/network/AbstractClientPlayerEntity;Lnet/minecraft/text/Text;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/LivingEntityRenderer;renderLabelIfPresent(Lnet/minecraft/entity/Entity;Lnet/minecraft/text/Text;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", ordinal = 1))
    public void addTierToNametag(Args args) {
        if (BrushCC.CONFIG.instance().fakeTier) {
            PlayerEntity entity = args.get(0);
            Text text = args.get(1);
            text = TierUtils.appendTier(entity, text);
            args.set(1, text);
        }
    }
}