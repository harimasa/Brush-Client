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
		LOGGER.info("""
				\n$$$$$__$$$$$__$$__$$__$$$$__$$__$$_____$$$$__$$_____$$$$$$_$$$$$_$$__$$_$$$$$$__$$$_
				  $$__$$_$$__$$_$$__$$_$$_____$$__$$____$$__$$_$$_______$$___$$____$$$_$$___$$____$$$_
				  $$$$$__$$$$$__$$__$$__$$$$__$$$$$$____$$_____$$_______$$___$$$$__$$_$$$___$$____$$$_
				  $$__$$_$$__$$_$$__$$_____$$_$$__$$____$$__$$_$$_______$$___$$____$$__$$___$$________
				  $$$$$__$$__$$__$$$$___$$$$__$$__$$_____$$$$__$$$$$$_$$$$$$_$$$$$_$$__$$___$$____$$$_ \s
				  \n \t\t\t\t\t\tBy\s""" + BrushClientUtils.getAuthors());
	}
}