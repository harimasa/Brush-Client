package ru.femboypig.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.femboypig.config.BrushCC;

@Mixin(EntityRenderer.class)
public abstract class MixinEntityRenderer<T extends Entity> {

    @Shadow
    @Final
    protected EntityRenderDispatcher dispatcher;

    @Shadow
    public abstract TextRenderer getTextRenderer();

    @Inject(method = "renderLabelIfPresent", at = @At("HEAD"))
    protected void renderLabelIfPresent(T entity, Text text, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (BrushCC.CONFIG.instance().nametags) {
            double d = this.dispatcher.getSquaredDistanceToCamera(entity);
            if (!(d > 4096.0)) {
                boolean sneak = !entity.isSneaky();
                float f = entity.getHeight() + 0.5F;
                matrices.push();
                matrices.translate(0.0F, f, 0.0F);
                matrices.multiply(this.dispatcher.getRotation());
                matrices.scale(-0.025F, -0.025F, 0.025F);
                Matrix4f matrix4f = matrices.peek().getPositionMatrix();
                float g = MinecraftClient.getInstance().options.getTextBackgroundOpacity(0.25F);
                int j = (int) (g * 255.0F) << 24;
                TextRenderer textRenderer = this.getTextRenderer();
                float h = (float) (-textRenderer.getWidth(text) / 2);
                j = (int) (BrushCC.CONFIG.instance().nametagsTransparent * 255.0F) << 24;
                if (BrushCC.CONFIG.instance().nametagsShadow) {
                    j = (int) (BrushCC.CONFIG.instance().nametagsTransparent / 2 * 255.0F) << 24;
                    textRenderer.draw(text, h, 0, 553648127, true, matrix4f, vertexConsumers, sneak ? TextRenderer.TextLayerType.SEE_THROUGH : TextRenderer.TextLayerType.NORMAL, j, light);
                    if (sneak) {
                        textRenderer.draw(text, h, 0, -1, true, matrix4f, vertexConsumers, TextRenderer.TextLayerType.NORMAL, 0, light);
                    }
                    if (sneak) {
                        textRenderer.draw(text, h, 0, -1, false, matrix4f, vertexConsumers, TextRenderer.TextLayerType.NORMAL, 0, light);
                    }
                } else {
                    textRenderer.draw(text, h, 0, 553648127, false, matrix4f, vertexConsumers, sneak ? TextRenderer.TextLayerType.SEE_THROUGH : TextRenderer.TextLayerType.NORMAL, j, light);
                    if (sneak) {
                        textRenderer.draw(text, h, 0, -1, false, matrix4f, vertexConsumers, TextRenderer.TextLayerType.NORMAL, 0, light);
                    }
                }
                matrices.pop();
            }
        }
    }
}