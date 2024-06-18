package ru.femboypig.utils;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.PlayerListEntry;
import ru.femboypig.utils.interfaces.instance;

public final class PlayerUtils implements instance {

    public static boolean invalid() {
        return mc == null || mc.player == null || mc.world == null || mc.interactionManager == null || mc.options == null;
    }

    public static ClientPlayerEntity player() {
        return mc.player;
    }

    public static long getPing() {
        if (invalid()) {
            return -1;
        }

        GameProfile p = player().getGameProfile();
        PlayerListEntry entry = player().networkHandler.getPlayerListEntry(p.getId());

        if (entry == null) {
            return -1;
        }

        return entry.getLatency();
    }

    public static int getFps() {
        return mc.getCurrentFps();
    }
}