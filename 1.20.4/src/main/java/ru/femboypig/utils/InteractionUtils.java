package ru.femboypig.utils;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public final class InteractionUtils {

    public static boolean canBreakCrystals() {
        if (PlayerUtils.invalid())
            return false;
        var p = PlayerUtils.player();

        StatusEffectInstance s = p.getStatusEffect(StatusEffects.STRENGTH);
        StatusEffectInstance w = p.getStatusEffect(StatusEffects.WEAKNESS);

        if (s == null && w == null) {
            return true;
        }
        if (w == null) {
            return true;
        }
        if (s != null && s.getAmplifier() > w.getAmplifier()) {
            return true;
        }

        return HotbarUtils.isHoldingTool();
    }
}