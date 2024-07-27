package ru.femboypig.mixins.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static ru.femboypig.utils.interfaces.Instance.mc;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {
    @Final @Shadow
    private Window window;

    @Inject(method = "getFramerateLimit", at = @At(value = "HEAD"), cancellable = true)
    public void antiLimit(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(this.window.getFramerateLimit());
    }

    @Inject(method = "tick", at = @At("RETURN"))
    public void runTick(CallbackInfo callbackInfo) {
        this.setTitle();
    }

    public void setTitle() {
        mc.getWindow().setTitle("Brush Client 1.4.6");
    }
}