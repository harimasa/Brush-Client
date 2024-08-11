package ru.femboypig.utils;

import net.minecraft.client.gui.DrawContext;

import static ru.femboypig.BrushClient.mc;

public class NTUtils {
    public static void drawOverlay(DrawContext context, int color) {
        context.getMatrices().push();
        context.getMatrices().translate(0 ,0, -9999);
        context.fill(0, 0, mc.getWindow().getWidth(), mc.getWindow().getHeight(), color);
        context.getMatrices().pop();
    }

}