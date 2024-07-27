package ru.femboypig.utils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import ru.femboypig.utils.interfaces.Instance;

public final class HotbarUtils implements Instance {

    public static boolean isHolding(Item item, Hand hand) {
        if (PlayerUtils.invalid()) {
            return false;
        }
        return PlayerUtils.player().getStackInHand(hand).isOf(item);
    }

    public static boolean nameContains(String contains) {
        return nameContains(contains, Hand.MAIN_HAND);
    }

    public static boolean nameContains(String contains, Hand hand) {
        if (PlayerUtils.invalid()) {
            return false;
        }
        ItemStack item = PlayerUtils.player().getStackInHand(hand);
        return item != null && item.getTranslationKey().toLowerCase().contains(contains.toLowerCase());
    }

    public static boolean isHoldingTool() {
        return nameContains("_sword") ||
                nameContains("_pickaxe") ||
                nameContains("_axe") ||
                nameContains("_hoe") ||
                nameContains("_shovel") ||
                nameContains("trident");
    }
}