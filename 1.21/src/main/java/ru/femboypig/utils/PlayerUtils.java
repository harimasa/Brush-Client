package ru.femboypig.utils;

import net.minecraft.client.network.ClientPlayerEntity;
import ru.femboypig.utils.interfaces.Instance;

public final class PlayerUtils implements Instance {

    public static boolean invalid() {
        return mc == null || mc.player == null || mc.world == null || mc.interactionManager == null || mc.options == null;
    }

    public static ClientPlayerEntity player() {
        return mc.player;
    }
}