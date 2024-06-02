package ru.femboypig.mixins.cape;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.femboypig.config.BrushCC;

import java.util.Map;

@Mixin(PlayerListEntry.class)
public abstract class MixinPlayerListEntry {

    @Accessor
    public abstract Map<MinecraftProfileTexture.Type, Identifier> getTextures();

    @Environment(EnvType.CLIENT)
    @Inject(method = "getCapeTexture", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/PlayerListEntry;loadTextures()V"))
    public void injectIntoTextures(CallbackInfoReturnable<Identifier> cir) {
        if (BrushCC.CONFIG.instance().capeEnabled) {
            Identifier cape = new Identifier("brush-client", "/textures/cape/cape.png");
            getTextures().put(MinecraftProfileTexture.Type.CAPE, cape);
        }
    }
}