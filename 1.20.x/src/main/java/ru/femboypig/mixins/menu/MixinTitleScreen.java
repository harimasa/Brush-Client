package ru.femboypig.mixins.menu;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.SplashTextRenderer;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.option.LanguageOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.femboypig.config.BrushCC;

@Mixin(TitleScreen.class)
public abstract class MixinTitleScreen extends Screen {
    @Shadow
    @Final
    @Mutable
    private static Identifier PANORAMA_OVERLAY;
    @Shadow
    @Nullable
    @Mutable
    private SplashTextRenderer splashText;

    @Shadow
    protected abstract void init();

    protected MixinTitleScreen(Text title) {
        super(title);
    }

    @Inject(method = "render", at = @At("HEAD"))
    protected void renderBackground(CallbackInfo info) {
        if (BrushCC.CONFIG.instance().bcm) {
            PANORAMA_OVERLAY = new Identifier("brush-client", "textures/menu/bc-preview.png");
            splashText = null;
        }
    }
}