package ru.femboypig.utils;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import ru.femboypig.config.BrushCC;

public class TierUtils {
    public static Text appendTier(PlayerEntity player, Text text) {
        MutableText following = Text.literal(BrushCC.CONFIG.instance().tier).styled(style -> style.withColor(BrushCC.CONFIG.instance().tierColor.getRGB()));
        following.append(Text.literal(" | ").formatted(Formatting.GRAY));
        String textContent = text.getString();
        return following.append(Text.literal(textContent).formatted(Formatting.WHITE));
    }
    // "HT1" -> 0xFF0000; // red
    // "LT1" -> 0xFFB6C1; // light pink
    // "HT2" -> 0xFFA500; // orange
    // "LT2" -> 0xFFE4B5; // light orange
    // "HT3" -> 0xDAA520; // goldenrod
    // "LT3" -> 0xEEE8AA; // pale goldenrod
    // "HT4" -> 0x006400; // dark green
    // "LT4" -> 0x90EE90; // light green
    // "HT5" -> 0x808080; // grey
    // "LT5" -> 0xD3D3D3; // pale grey
}