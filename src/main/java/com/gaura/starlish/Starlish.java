package com.gaura.starlish;

import com.gaura.starlish.config.StarlishConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;

public class Starlish implements ModInitializer {

	public static final String MOD_ID = "starlish";
	public static StarlishConfig CONFIG = new StarlishConfig();

	@Override
	public void onInitialize() {

		// Registering Config
		AutoConfig.register(StarlishConfig.class, JanksonConfigSerializer::new);
		CONFIG = AutoConfig.getConfigHolder(StarlishConfig.class).getConfig();
	}
}