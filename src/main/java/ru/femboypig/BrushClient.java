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
	public static final String MOD_VERSION = "1.4.11";

	@Override
	public void onInitialize() {
		DiscordClient.init();
		BrushCC.CONFIG.load();
		ClientTickEvents.END_WORLD_TICK.register((client) -> {
			OverlayReloadListener.callEvent();
		});
		LOGGER.info("""
			\n$$$$$__$$$$$__$$__$$__$$$$__$$__$$_____$$$$__$$_____$$$$$$_$$$$$_$$__$$_$$$$$$__$$$_
			  $$__$$_$$__$$_$$__$$_$$_____$$__$$____$$__$$_$$_______$$___$$____$$$_$$___$$____$$$_
			  $$$$$__$$$$$__$$__$$__$$$$__$$$$$$____$$_____$$_______$$___$$$$__$$_$$$___$$____$$$_
			  $$__$$_$$__$$_$$__$$_____$$_$$__$$____$$__$$_$$_______$$___$$____$$__$$___$$________
			  $$$$$__$$__$$__$$$$___$$$$__$$__$$_____$$$$__$$$$$$_$$$$$$_$$$$$_$$__$$___$$____$$$_ \s
			  \n \t\t\t\t\t\tBy:\s""" + BrushClientUtils.getAuthors());
	}
}