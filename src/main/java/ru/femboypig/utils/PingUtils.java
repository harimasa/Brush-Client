package ru.femboypig.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.PlayerListEntry;

public class PingUtils {

    public static int getLocalPing() {
        ClientPlayNetworkHandler networkHandler = MinecraftClient.getInstance().getNetworkHandler();
        if (networkHandler == null) {
            return -1;
        }

        PlayerListEntry localPlayer = networkHandler.getPlayerListEntry(networkHandler.getProfile().getId());
        if (localPlayer == null) {
            return -1;
        }

        return localPlayer.getLatency();
    }
}