package ru.femboypig.mixins;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.realms.dto.PlayerInfo;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Colors;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.femboypig.config.BrushCC;

import ru.femboypig.utils.PingUtils;
import ru.femboypig.utils.interfaces.instance;

import java.util.Objects;

@Mixin(InGameHud.class)
public abstract class MixinInGameHud implements instance {
    protected abstract void logo(MatrixStack matrices, PlayerEntity player, int x, int y, int lines, int absorption, boolean blinking);
    @Shadow
    public abstract TextRenderer getTextRenderer();
    public PlayerInfo entry;

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

    @Inject(method = "render", at = @At("TAIL"))
    public void render(DrawContext context, float tickDelta, CallbackInfo info) {
        if (!mc.options.hudHidden && BrushCC.CONFIG.instance().fps) {
            String text = null;
            if (BrushCC.CONFIG.instance().fps) {
                text = mc.getCurrentFps() + BrushCC.CONFIG.instance().nameFps;
            }

            int textPosX = BrushCC.CONFIG.instance().left;
            int textPosY = BrushCC.CONFIG.instance().top;

            double guiScale = mc.getWindow().getScaleFactor();
            if (guiScale > 0) {
                textPosX /= guiScale;
                textPosY /= guiScale;
            }

            int maxTextPosX = mc.getWindow().getScaledWidth() - mc.textRenderer.getWidth(text);
            int maxTextPosY = mc.getWindow().getScaledHeight() - mc.textRenderer.fontHeight;
            textPosX = Math.min(textPosX, maxTextPosX);
            textPosY = Math.min(textPosY, maxTextPosY);

            this.renderText(context, mc.textRenderer, text, textPosX, textPosY, BrushCC.CONFIG.instance().fpsColor.getRGB(), BrushCC.CONFIG.instance().textSize, BrushCC.CONFIG.instance().textShadow);
        }
        if (!mc.options.hudHidden && BrushCC.CONFIG.instance().ping) {
            String text = null;
            if (BrushCC.CONFIG.instance().ping) {
                text = Objects.toString(PingUtils.getLocalPing() + BrushCC.CONFIG.instance().name) ;
            }

            int textPosX = BrushCC.CONFIG.instance().leftPing;
            int textPosY = BrushCC.CONFIG.instance().topPing;

            double guiScale = mc.getWindow().getScaleFactor();
            if (guiScale > 0) {
                textPosX /= guiScale;
                textPosY /= guiScale;
            }

            int maxTextPosX = mc.getWindow().getScaledWidth() - mc.textRenderer.getWidth(text);
            int maxTextPosY = mc.getWindow().getScaledHeight() - mc.textRenderer.fontHeight;
            textPosX = Math.min(textPosX, maxTextPosX);
            textPosY = Math.min(textPosY, maxTextPosY);

            this.renderText(context, mc.textRenderer, text, textPosX, textPosY, BrushCC.CONFIG.instance().pingColor.getRGB(), BrushCC.CONFIG.instance().pingSize, BrushCC.CONFIG.instance().pingShadow);
        }
        if (!mc.options.hudHidden && BrushCC.CONFIG.instance().coords) {
            String text = null;
            if (BrushCC.CONFIG.instance().coords) {
                text = Objects.toString(Math.round(mc.player.getX()) + " " + (Math.round(mc.player.getY()) + " " + (Math.round(mc.player.getZ()))));
            }

            int textPosX = BrushCC.CONFIG.instance().leftCoords;
            int textPosY = BrushCC.CONFIG.instance().topCoords;

            double guiScale = mc.getWindow().getScaleFactor();
            if (guiScale > 0) {
                textPosX /= guiScale;
                textPosY /= guiScale;
            }

            int maxTextPosX = mc.getWindow().getScaledWidth() - mc.textRenderer.getWidth(text);
            int maxTextPosY = mc.getWindow().getScaledHeight() - mc.textRenderer.fontHeight;
            textPosX = Math.min(textPosX, maxTextPosX);
            textPosY = Math.min(textPosY, maxTextPosY);

            this.renderText(context, mc.textRenderer, text, textPosX, textPosY, BrushCC.CONFIG.instance().coordsColor.getRGB(), BrushCC.CONFIG.instance().coordsSize, BrushCC.CONFIG.instance().coordsShadow);
        }
    }

    @Unique
    private void renderText(DrawContext context, TextRenderer textRenderer, String text, int x, int y, int color, float scale, boolean shadowed) {
        if (scale != 1.0f) {
            MatrixStack matrixStack = context.getMatrices();
            matrixStack.push();
            matrixStack.translate(x, y, 0);
            matrixStack.scale(scale, scale, scale);
            matrixStack.translate(-x, -y, 0);
            context.drawText(textRenderer, text, x, y, color, shadowed);
            matrixStack.pop();
        }
        else {
            context.drawText(textRenderer, text, x, y, color, shadowed);
        }
    }
}