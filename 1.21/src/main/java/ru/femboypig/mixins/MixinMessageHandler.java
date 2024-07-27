package ru.femboypig.mixins;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.message.MessageHandler;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.femboypig.config.BrushCC;
import ru.femboypig.modules.misc.PingMe;

import static ru.femboypig.utils.interfaces.Instance.mc;

@Mixin(MessageHandler.class)
public class MixinMessageHandler {
    private static String name = mc.getInstance().getSession().getUsername().toLowerCase();

    @Inject(method = "onChatMessage", at = @At("RETURN"))
    public void message(SignedMessage message, GameProfile sender, MessageType.Parameters params, CallbackInfo ci) {
        String msg = message.getSignedContent().toLowerCase();
        if (BrushCC.CONFIG.instance().pingMe && msg.contains(name)) {
            mc.player.sendMessage(Text.translatable("brushclient.misc.pingme.text").styled(s -> s.withColor(BrushCC.CONFIG.instance().pingMeColor.getRGB()).withBold(true).withItalic(true)));
            PingMe.playSound();
        }
    }
}