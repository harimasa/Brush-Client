package ru.femboypig.mixins.menu;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static ru.femboypig.BrushClient.MOD_VERSION;
import static ru.femboypig.utils.interfaces.instance.mc;

@Mixin(DrawContext.class)
public class MixinDrawContext {
    @Shadow
    public int drawText(TextRenderer textRenderer, @Nullable String string, int i, int j, int k, boolean bl) {
        return 0;
    }

    @Inject(at = @At("HEAD"), method = "drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;III)I", cancellable = true)
    public void drawTextWithShadow(TextRenderer textRenderer, String string, int i, int j, int k, CallbackInfoReturnable<Integer> cir) {
        if (string.startsWith("Minecraft 1.20.1/Fabric ("))
            cir.setReturnValue(drawText(textRenderer, "Brush Client " + MOD_VERSION + " / Fabric", i, j, k, true));
    }
}