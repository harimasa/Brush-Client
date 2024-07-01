package ru.femboypig.utils;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import ru.femboypig.config.BrushCC;

import java.text.DecimalFormat;

public class HPUtils {
    public static Text appendHP(PlayerEntity player, Text text) {
        double health = player.getHealth();
        DecimalFormat df = new DecimalFormat("#.#"); // Pattern for one decimal place
        String formattedHealth = df.format(health);
        MutableText following = Text.literal(String.valueOf("❤" + formattedHealth)).styled(style -> style.withColor(BrushCC.CONFIG.instance().hpColor.getRGB()));
        following.append(Text.literal(" | ").formatted(Formatting.GRAY));
        String textContent = text.getString();
        return following.append(Text.literal(textContent).formatted(Formatting.WHITE));
    }
}