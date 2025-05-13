package com.starfish_studios.yaf.payload;

import com.starfish_studios.yaf.YetAnotherFurniture;
import com.starfish_studios.yaf.block.entity.MailboxBlockEntity;
import dev.architectury.networking.NetworkManager;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record MailboxC2SPayload(String string, BlockPos pos) implements CustomPacketPayload {
    public static final Type<MailboxC2SPayload> TYPE = new Type<>(YetAnotherFurniture.id("mailbox"));

    public static final StreamCodec<RegistryFriendlyByteBuf, MailboxC2SPayload> STREAM_CODEC =
            CustomPacketPayload.codec(
                    MailboxC2SPayload::write,
                    buf -> new MailboxC2SPayload(buf.readUtf(), buf.readBlockPos())
            );

    private void write(RegistryFriendlyByteBuf buf) {
        buf.writeUtf(this.string);
        buf.writeBlockPos(this.pos);
    }

    public static void handleC2S(MailboxC2SPayload payload, NetworkManager.PacketContext context) {
        var level = context.getPlayer().level();
        var pos = payload.pos;
        var string = payload.string;

        if (level.getBlockEntity(pos) instanceof MailboxBlockEntity mailbox) {
            mailbox.targetString = string;
        }
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }


}
