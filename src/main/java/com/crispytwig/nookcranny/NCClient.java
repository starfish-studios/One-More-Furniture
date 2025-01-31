package com.crispytwig.nookcranny;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class NCClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		NCVanillaIntegration.Client.clientInit();
	}
}