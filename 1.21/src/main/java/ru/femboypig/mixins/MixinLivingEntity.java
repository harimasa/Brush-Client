package ru.femboypig.mixins;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import ru.femboypig.config.BrushCC;

@Mixin(LivingEntity.class)
public class MixinLivingEntity {
    @ModifyConstant(method = "isBlocking", constant = @Constant(intValue = 5))
    private int setShieldUseDelay(int constant) {
        return BrushCC.CONFIG.instance().noShieldDelay ? 1 : constant;
    }
}