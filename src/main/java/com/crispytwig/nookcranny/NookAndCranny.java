package com.crispytwig.nookcranny;

import com.crispytwig.nookcranny.blocks.entities.MailboxBlockEntity;
import com.crispytwig.nookcranny.client.gui.screens.widget.LockTargetMailboxWidget;
import com.crispytwig.nookcranny.events.*;
import com.crispytwig.nookcranny.inventory.MailboxMenu;
import com.crispytwig.nookcranny.registry.NCEntities;
import com.crispytwig.nookcranny.registry.*;
import com.google.common.reflect.Reflection;
import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.mehvahdjukaar.every_compat.EveryCompat;


public class NookAndCranny implements ModInitializer{
	public static final String MOD_ID = "nookcranny";

	@Override
	public void onInitialize() {
		MidnightConfig.init(MOD_ID, NCConfig.class);

		if(FabricLoader.getInstance().isModLoaded("everycomp")){
			EveryCompat.ACTIVE_MODULES.put(MOD_ID, new NCModule(MOD_ID));
		}


	}


}