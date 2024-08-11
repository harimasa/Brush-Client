package ru.femboypig.mixins;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.TntEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.TntEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.femboypig.config.BrushCC;

import java.text.DecimalFormat;

@Mixin(TntEntityRenderer.class)
public abstract class MixinTntEntityRenderer extends EntityRenderer<TntEntity> {
    private static final DecimalFormat decimalFormat = new DecimalFormat("#.##");
    protected MixinTntEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Inject(method = "render(Lnet/minecraft/entity/TntEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/EntityRenderer;render(Lnet/minecraft/entity/Entity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"))
    private void renderTntLabel(TntEntity entity, float yaw, float delta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, CallbackInfo ci) {
        if (BrushCC.CONFIG.instance().tnt) {
            super.renderLabelIfPresent(entity, getTime(entity.getFuse()), matrixStack, vertexConsumerProvider, light);
        }
    }
    private static Text getTime(double tick) {
        double timing = tick / 20;
        return Text.of(decimalFormat.format(timing)).copy().formatted(Formatting.RED);
    }
}