package com.gaura.starlish;

import com.gaura.starlish.config.StarlishConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Starlish implements ModInitializer {

	public static final String MOD_ID = "starlish";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static StarlishConfig CONFIG = new StarlishConfig();

	@Override
	public void onInitialize() {

		// Registering Config
		AutoConfig.register(StarlishConfig.class, JanksonConfigSerializer::new);
		CONFIG = AutoConfig.getConfigHolder(StarlishConfig.class).getConfig();
	}

	public static int getLevelColor(int level) {

		switch (level) {

			case 1 -> {

				return Starlish.CONFIG.level_1_color;
			}
			case 2 -> {

				return Starlish.CONFIG.level_2_color;
			}
			case 3 -> {

				return Starlish.CONFIG.level_3_color;
			}
			case 4 -> {

				return Starlish.CONFIG.level_4_color;
			}
			case 5 -> {

				return Starlish.CONFIG.level_5_color;
			}
			case 6 -> {

				return Starlish.CONFIG.level_6_color;
			}
			case 7 -> {

				return Starlish.CONFIG.level_7_color;
			}
			case 8 -> {

				return Starlish.CONFIG.level_8_color;
			}
			case 9 -> {

				return Starlish.CONFIG.level_9_color;
			}
			case 10 -> {

				return Starlish.CONFIG.level_10_color;
			}
			default -> {

				return 0xFFFFFF;
			}
		}
	}
}