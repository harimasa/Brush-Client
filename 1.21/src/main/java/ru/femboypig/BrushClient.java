package ru.femboypig;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.femboypig.config.BrushCC;
import ru.femboypig.discord.DiscordClient;
import ru.femboypig.utils.listeners.OverlayReloadListener;

public class BrushClient implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("brush-client");
	public static final String MOD_VERSION = "1.4.6";

	@Override
	public void onInitialize() {
		DiscordClient.init();
		BrushCC.CONFIG.load();
		ClientTickEvents.END_WORLD_TICK.register((client) -> {
			OverlayReloadListener.callEvent();
		});
	}
}