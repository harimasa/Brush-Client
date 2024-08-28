package ru.femboypig.mixins;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Items;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.util.Colors;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import ru.femboypig.config.BrushCC;
import ru.femboypig.utils.NTUtils;
import ru.femboypig.utils.PingUtils;

import java.util.Objects;

import static ru.femboypig.BrushClient.mc;

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
                text = Objects.toString(PingUtils.getLocalPing() + BrushCC.CONFIG.instance().name);
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
        if (!mc.options.hudHidden && BrushCC.CONFIG.instance().ip) {
            String text = null;
            if (BrushCC.CONFIG.instance().ip) {
                text = mc.getCurrentServerEntry().address;
            }

            int textPosX = BrushCC.CONFIG.instance().leftIp;
            int textPosY = BrushCC.CONFIG.instance().topIp;

            double guiScale = mc.getWindow().getScaleFactor();
            if (guiScale > 0) {
                textPosX /= guiScale;
                textPosY /= guiScale;
            }

            int maxTextPosX = mc.getWindow().getScaledWidth() - mc.textRenderer.getWidth(text);
            int maxTextPosY = mc.getWindow().getScaledHeight() - mc.textRenderer.fontHeight;
            textPosX = Math.min(textPosX, maxTextPosX);
            textPosY = Math.min(textPosY, maxTextPosY);

            this.renderText(context, mc.textRenderer, text, textPosX, textPosY, BrushCC.CONFIG.instance().ipColor.getRGB(), BrushCC.CONFIG.instance().ipSize, BrushCC.CONFIG.instance().ipShadow);
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

    @Inject(method = "render", at = @At("HEAD"))
    public void onTick(DrawContext context, float tickDelta, CallbackInfo ci) {
        if (BrushCC.CONFIG.instance().nototem) {
            if (mc.player != null) {
                if (mc.player.getOffHandStack().getItem() != Items.TOTEM_OF_UNDYING && mc.player.getMainHandStack().getItem() != Items.TOTEM_OF_UNDYING) {
                    NTUtils.drawOverlay(context, BrushCC.CONFIG.instance().nototemColor.getRGB());
                }
            }
        }
    }

    @Inject(method = "renderPortalOverlay", at = @At("HEAD"), cancellable = true)
    private void noPortalOverlay(DrawContext context, float nauseaStrength, CallbackInfo ci) {
        if (BrushCC.CONFIG.instance().overlays && BrushCC.CONFIG.instance().portalOverlay) {
            ci.cancel();
        }
    }

    @ModifyArgs(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderOverlay(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/util/Identifier;F)V", ordinal = 0))
    private void noPumpkinOverlay(Args args) {
        if (BrushCC.CONFIG.instance().overlays && BrushCC.CONFIG.instance().pumpkinOverlay) {
            args.set(2, 0f);
        }
    }

    @Inject(method = "renderScoreboardSidebar", at = @At("HEAD"), cancellable = true)
    private void onRenderScoreboardSidebar(DrawContext context, ScoreboardObjective objective, CallbackInfo ci) {
        if (BrushCC.CONFIG.instance().overlays && BrushCC.CONFIG.instance().scoreboardOverlay) {
            ci.cancel();
        }
    }

    @Unique
    public void renderText(DrawContext context, TextRenderer textRenderer, String text, int x, int y, int color, float scale, boolean shadowed) {
        if (scale != 1.0f) {
            MatrixStack matrixStack = context.getMatrices();
            matrixStack.push();
            matrixStack.translate(x, y, 0);
            matrixStack.scale(scale, scale, scale);
            matrixStack.translate(-x, -y, 0);
            context.drawText(textRenderer, text, x, y, color, shadowed);
            matrixStack.pop();
        } else {
            context.drawText(textRenderer, text, x, y, color, shadowed);
        }
    }
}