package ru.femboypig.mixins;

import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import ru.femboypig.config.BrushCC;

@Mixin(WorldRenderer.class)
public class MixinWorldRenderer {
    @ModifyArgs(method = "drawBlockOutline", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/WorldRenderer;drawCuboidShapeOutline(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;Lnet/minecraft/util/shape/VoxelShape;DDDFFFF)V"))
    private void changeColors(Args args) {
        if (BrushCC.CONFIG.instance().bo) {
            int red = BrushCC.CONFIG.instance().boColor.getRed();
            int green = BrushCC.CONFIG.instance().boColor.getGreen();
            int blue = BrushCC.CONFIG.instance().boColor.getBlue();
            int alpha = BrushCC.CONFIG.instance().boColor.getAlpha();

            args.set(6, (red / 255F));
            args.set(7, (green / 255F));
            args.set(8, (blue / 255F));
            args.set(9, (alpha / 255F));
        }
    }
}