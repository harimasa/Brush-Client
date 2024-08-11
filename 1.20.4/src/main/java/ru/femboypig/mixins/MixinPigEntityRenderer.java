package ru.femboypig.mixins;

import net.minecraft.client.render.entity.PigEntityRenderer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.femboypig.config.BrushCC;

@Mixin(PigEntityRenderer.class)
public class MixinPigEntityRenderer {

    @Inject(method = "getTexture", at = @At("HEAD"), cancellable = true)
    private void changePigTexture(CallbackInfoReturnable<Identifier> cir) {
        if (BrushCC.CONFIG.instance().blackPigEnabled) {
            Identifier newTexture = new Identifier("brush-client", "textures/black_pig.png");
            cir.setReturnValue(newTexture);
        }
    }
}