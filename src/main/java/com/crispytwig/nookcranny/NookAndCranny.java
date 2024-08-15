package com.crispytwig.nookcranny;

import com.crispytwig.nookcranny.registry.NCEntities;
import com.crispytwig.nookcranny.registry.*;
import com.google.common.reflect.Reflection;
import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;


public class NookAndCranny implements ModInitializer{
	public static final String MOD_ID = "nookcranny";

	@Override
	public void onInitialize() {
		MidnightConfig.init(MOD_ID, NCConfig.class);
		Reflection.initialize(
				NCCreativeModeTab.class,
				NCSoundEvents.class,
				NCItems.class,
				NCEntities.class
		);
		NCVanillaIntegration.serverInit();
	}


}