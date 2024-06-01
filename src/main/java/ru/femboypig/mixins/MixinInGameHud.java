package ru.femboypig.mixins;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.Colors;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.femboypig.config.BrushCC;

@Mixin(InGameHud.class)
public abstract class MixinInGameHud {
    @Shadow
    public abstract TextRenderer getTextRenderer();

    @Inject(method = "renderStatusEffectOverlay", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/texture/StatusEffectSpriteManager;getSprite(Lnet/minecraft/entity/effect/StatusEffect;)Lnet/minecraft/client/texture/Sprite;"))
    private void drawText(DrawContext context, CallbackInfo ci, @Local StatusEffectInstance effect, @Local(ordinal = 2) int x, @Local(ordinal = 3) int y) {
        if (BrushCC.CONFIG.instance().effectTimeEnabled) {

            context.drawCenteredTextWithShadow(getTextRenderer(), ticksToTime(effect.getDuration()), x + 12, y + 15, Colors.GRAY);
        }
    }

    @Unique
    private String ticksToTime(int ticks) {
        int seconds = ticks / 20;
        int minutes = seconds / 60;
        seconds = seconds % 60;
        if (minutes == 0) {
            return seconds + "";
        }
        return minutes + ":" + String.format("%2s", seconds).replace(' ', '0');
    }
}