package ru.femboypig.utils;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import ru.femboypig.config.BrushCC;

public class HPUtils {
    public static Text appendHP(PlayerEntity player, Text text) {
        MutableText following = Text.literal(String.valueOf("❤" + player.getHealth())).styled(style -> style.withColor(BrushCC.CONFIG.instance().hpColor.getRGB()));
        following.append(Text.literal(" | ").formatted(Formatting.GRAY));
        String textContent = text.getString();
        return following.append(Text.literal(textContent).formatted(Formatting.WHITE));
    }
}