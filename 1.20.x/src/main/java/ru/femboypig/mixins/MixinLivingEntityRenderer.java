package ru.femboypig.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.femboypig.config.BrushCC;
import ru.femboypig.utils.interfaces.Instance;

@Mixin(LivingEntityRenderer.class)
public abstract class MixinLivingEntityRenderer implements Instance {
    @Inject(method = "hasLabel(Lnet/minecraft/entity/LivingEntity;)Z", at = @At("RETURN"), cancellable = true)
    public void hasLabel(LivingEntity ent, CallbackInfoReturnable<Boolean> cir) {
        if (BrushCC.CONFIG.instance().renderNameEnabled && mc.getCameraEntity() == ent) {
            cir.setReturnValue(MinecraftClient.isHudEnabled());
        }
    }
}