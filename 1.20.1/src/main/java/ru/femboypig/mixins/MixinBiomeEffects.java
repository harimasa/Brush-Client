package ru.femboypig.mixins;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.biome.BiomeEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import ru.femboypig.config.BrushCC;

@Mixin(BiomeEffects.class)
public abstract class MixinBiomeEffects {
    @ModifyReturnValue(method = "getSkyColor", at = @At("RETURN"))
    private int getSkyColor(int original) {
        if (BrushCC.CONFIG.instance().sky) {
            return BrushCC.CONFIG.instance().skyColor.getRGB();
        }
        return original;
    }

    @ModifyReturnValue(method = "getFogColor", at = @At("RETURN"))
    private int getFogColor(int original) {
        if (BrushCC.CONFIG.instance().fog) {
            return BrushCC.CONFIG.instance().fogColor.getRGB();
        }
        return original;
    }
}