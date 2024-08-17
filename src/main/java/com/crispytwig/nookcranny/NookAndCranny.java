package com.crispytwig.nookcranny;

import com.crispytwig.nookcranny.events.DyeSofa;
import com.crispytwig.nookcranny.registry.NCEntities;
import com.crispytwig.nookcranny.registry.*;
import com.google.common.reflect.Reflection;
import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.Util;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;
import java.util.Map;


public class NookAndCranny implements ModInitializer{
	public static final String MOD_ID = "nookcranny";

	@Override
	public void onInitialize() {
		MidnightConfig.init(MOD_ID, NCConfig.class);
		Reflection.initialize(
				NCCreativeModeTab.class,
				NCSoundEvents.class,
				NCItems.class,
				NCBlocks.class,
				NCBlockEntities.class,
				NCEntities.class
		);

		UseBlockCallback.EVENT.register(new DyeSofa());
		NCVanillaIntegration.serverInit();
	}


}