package ru.femboypig.utils.interfaces;

import net.minecraft.client.MinecraftClient;

public interface Instance {
    MinecraftClient mc = MinecraftClient.getInstance();
}