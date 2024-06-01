package ru.femboypig.mixins;

import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.femboypig.config.BrushCC;

@Mixin(LightmapTextureManager.class)
public abstract class MixinLightmapTextureManager {

    @Inject(method = "getBrightness", at = @At("RETURN"), cancellable = true)
    private static void getBrightness(DimensionType type, int lightLevel, CallbackInfoReturnable<Float> cir) {
        if (BrushCC.CONFIG.instance().fullBrightEnabled) {
            cir.setReturnValue(1.0F);
        }
    }
}