package ru.femboypig.discord;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import ru.femboypig.config.BrushCC;

import java.util.Timer;
import java.util.TimerTask;

public class DiscordClient {
    public static final MinecraftClient mc = MinecraftClient.getInstance();
    public static final DiscordRPC brush = DiscordRPC.INSTANCE;
    public static final DiscordEventHandlers handlers = new DiscordEventHandlers();
    public static Long stg;
    private static final Timer TIMER = new Timer();
    private static String exp;

    public static void init() {
        stg = System.currentTimeMillis() / 1000;
        ClientLifecycleEvents.CLIENT_STOPPING.register(client -> {
            brush.Discord_Shutdown();
        });
        brush.Discord_Initialize("1246201544815415397", handlers, true, null);
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                brush.Discord_RunCallbacks();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {
                }
            }
        }, "RPC").start();
        start();
    }

    private static void start() {
        TIMER.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    updatePresence();
                    if (exp != null) exp = null;
                } catch (Exception e) {
                    if (exp == null || !exp.equals(e.getMessage())) {
                        e.printStackTrace();
                        DiscordRichPresence presence = new DiscordRichPresence();
                        presence.details = "Disable";
                        brush.Discord_UpdatePresence(presence);
                        exp = e.getMessage();
                    }
                }
            }
        }, 2500, 2500);
    }

    private static void updatePresence() {
        if (BrushCC.CONFIG.instance().rpc) {
            if (mc.world == null || mc.player == null) {
                if (States.getGameState() == 1) {
                    DiscordRichPresence presence = new DiscordRichPresence();
                    presence.details = "Playing Minecraft " + SharedConstants.getGameVersion().getName();
                    presence.state = "Loading game";
                    DiscordClient.updateDiscordPresence(presence);
                } else if (States.getGameState() == 2) {
                    DiscordRichPresence presence = new DiscordRichPresence();
                    presence.details = "Playing Minecraft " + SharedConstants.getGameVersion().getName();
                    presence.state = "Connecting to a server";
                    DiscordClient.updateDiscordPresence(presence);
                } else if (States.getGameState() == 3) {
                    DiscordRichPresence presence = new DiscordRichPresence();
                    presence.details = "Playing Minecraft " + SharedConstants.getGameVersion().getName();
                    presence.state = "Disconnected from a server";
                    DiscordClient.updateDiscordPresence(presence);
                } else {
                    DiscordRichPresence presence = new DiscordRichPresence();
                    presence.details = "Playing Minecraft " + SharedConstants.getGameVersion().getName();
                    presence.state = "In the main menu";
                    DiscordClient.updateDiscordPresence(presence);
                }
            } else {
                if (mc.isInSingleplayer()) {
                    DiscordRichPresence presence = new DiscordRichPresence();
                    presence.details = "Playing Minecraft " + SharedConstants.getGameVersion().getName();
                    presence.state = "Singleplayer";
                    DiscordClient.updateDiscordPresence(presence);
                } else if (mc.getCurrentServerEntry() != null) {
                    DiscordRichPresence presence = new DiscordRichPresence();
                    presence.details = "Playing Minecraft " + SharedConstants.getGameVersion().getName();
                    presence.state = mc.getCurrentServerEntry().address;
                    DiscordClient.updateDiscordPresence(presence);
                } else {
                    DiscordRichPresence presence = new DiscordRichPresence();
                    presence.details = "Playing Minecraft " + SharedConstants.getGameVersion().getName();
                    presence.state = "In the main menu";
                    DiscordClient.updateDiscordPresence(presence);
                }
            }
        } else {
            updateDiscordPresence(null);
        }
    }

    public static void updateDiscordPresence(DiscordRichPresence presence){
        presence.largeImageKey = "logo";
        presence.largeImageText = "A very colorful client!";
        presence.startTimestamp = stg;
        brush.Discord_UpdatePresence(presence);
    }
}