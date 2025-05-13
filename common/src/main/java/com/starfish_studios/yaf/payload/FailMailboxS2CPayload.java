package com.starfish_studios.yaf.payload;

import com.starfish_studios.yaf.YetAnotherFurniture;
import com.starfish_studios.yaf.block.entity.MailboxBlockEntity;
import dev.architectury.networking.NetworkManager;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.level.block.entity.BlockEntity;

public record FailMailboxS2CPayload(boolean state, BlockPos pos) implements CustomPacketPayload {
    public static final Type<FailMailboxS2CPayload> TYPE = new Type<>(YetAnotherFurniture.id("fail_mail"));

    public static final StreamCodec<RegistryFriendlyByteBuf, FailMailboxS2CPayload> STREAM_CODEC =
            CustomPacketPayload.codec(
                    FailMailboxS2CPayload::write,
                    buf -> new FailMailboxS2CPayload(buf.readBoolean(), buf.readBlockPos())
            );

    private void write(RegistryFriendlyByteBuf buf) {
        buf.writeBoolean(this.state);
        buf.writeBlockPos(this.pos);
    }

    public static void handleS2C(FailMailboxS2CPayload payload, NetworkManager.PacketContext context) {
        var pos = payload.pos;
        var state = payload.state;
        var client = Minecraft.getInstance();
        client.execute(() -> {
            if (client.level != null) {
                BlockEntity be = client.level.getBlockEntity(pos);
                if (be instanceof MailboxBlockEntity mailbox) {
                    mailbox.failedToSend = state;
                    mailbox.setChanged();
                }
            }

        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
