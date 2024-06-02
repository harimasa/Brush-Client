package ru.femboypig;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.femboypig.config.BrushCC;
import ru.femboypig.discord.DiscordClient;

public class BrushClient implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("brush-client");

	@Override
	public void onInitialize() {
		DiscordClient.init();
		BrushCC.CONFIG.load();
		LOGGER.info("\t\tBrush Client Enable!❤\n\tAuthor: femboyPig");
	}
}