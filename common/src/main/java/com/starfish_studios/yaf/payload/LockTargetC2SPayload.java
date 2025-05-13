package com.starfish_studios.yaf.payload;

import com.starfish_studios.yaf.YetAnotherFurniture;
import com.starfish_studios.yaf.block.entity.MailboxBlockEntity;
import dev.architectury.networking.NetworkManager;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record LockTargetC2SPayload(boolean lock, BlockPos pos) implements CustomPacketPayload {
    public static final Type<LockTargetC2SPayload> TYPE = new Type<>(YetAnotherFurniture.id("lock_target"));

    public static final StreamCodec<RegistryFriendlyByteBuf, LockTargetC2SPayload> STREAM_CODEC =
            CustomPacketPayload.codec(
                    LockTargetC2SPayload::write,
                    buf -> new LockTargetC2SPayload(buf.readBoolean(), buf.readBlockPos())
            );

    private void write(RegistryFriendlyByteBuf buf) {
        buf.writeBoolean(this.lock);
        buf.writeBlockPos(this.pos);
    }

    public static void handleC2S(LockTargetC2SPayload payload, NetworkManager.PacketContext context) {
        var level = context.getPlayer().level();
        var pos = payload.pos;
        var lock = payload.lock;

        if (level.getBlockEntity(pos) instanceof MailboxBlockEntity mailbox) {
            mailbox.lockTarget = lock;
        }
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }


}
