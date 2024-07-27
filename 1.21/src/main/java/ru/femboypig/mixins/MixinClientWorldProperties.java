package ru.femboypig.mixins;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.femboypig.config.BrushCC;

@Mixin(ClientWorld.Properties.class)
public abstract class MixinClientWorldProperties {
    @Inject(at = @At("RETURN"), method = "getTimeOfDay", cancellable = true)
    @Environment(EnvType.CLIENT)
    public void getTime(CallbackInfoReturnable<Long> cir) {
        if (BrushCC.CONFIG.instance().timeChangerEnabled) {
            cir.setReturnValue((long) BrushCC.CONFIG.instance().timeChanger);
        }
    }
}