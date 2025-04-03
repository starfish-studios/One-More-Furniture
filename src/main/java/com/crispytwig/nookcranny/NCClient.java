package com.crispytwig.nookcranny;

import com.crispytwig.nookcranny.blocks.entities.MailboxBlockEntity;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

@Environment(EnvType.CLIENT)
public class NCClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		NCVanillaIntegration.Client.clientInit();
	}
}