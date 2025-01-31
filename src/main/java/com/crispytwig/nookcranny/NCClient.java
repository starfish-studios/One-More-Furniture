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

		ClientPlayNetworking.registerGlobalReceiver(MailboxBlockEntity.packetChannel, (client, handler, buf, responseSender) -> {
			var pos = buf.readBlockPos();
			var state = buf.readBoolean();
			client.execute(() -> {
				var be = client.level.getBlockEntity(pos);
				if (be instanceof MailboxBlockEntity mailbox) {
					mailbox.failedToSend = state;
					mailbox.setChanged();
				}
			});
		});

		ClientPlayNetworking.registerGlobalReceiver(MailboxBlockEntity.packetChannel2, (client, handler, buf, responseSender) -> {
			var pos = buf.readBlockPos();
			client.execute(() -> {
				var be = client.level.getBlockEntity(pos);
				if (be instanceof MailboxBlockEntity mailbox) {
					mailbox.targetString = "";
					mailbox.setChanged();
				}
			});
		});
	}
}