package com.starfish_studios.yaf.registry;

import com.starfish_studios.yaf.payload.FailMailboxS2CPayload;
import com.starfish_studios.yaf.payload.LockTargetC2SPayload;
import com.starfish_studios.yaf.payload.MailboxC2SPayload;
import com.starfish_studios.yaf.payload.ResetMailboxS2CPayload;
import dev.architectury.networking.NetworkManager;
import dev.architectury.platform.Platform;
import dev.architectury.utils.Env;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public class YAFPayloads {

    public static void register(){
        registerC2S(MailboxC2SPayload.TYPE, MailboxC2SPayload.STREAM_CODEC, MailboxC2SPayload::handleC2S);
        registerC2S(LockTargetC2SPayload.TYPE, LockTargetC2SPayload.STREAM_CODEC, LockTargetC2SPayload::handleC2S);

        registerS2C(FailMailboxS2CPayload.TYPE, FailMailboxS2CPayload.STREAM_CODEC, FailMailboxS2CPayload::handleS2C);
        registerS2C(ResetMailboxS2CPayload.TYPE, ResetMailboxS2CPayload.STREAM_CODEC, ResetMailboxS2CPayload::handleS2C);
    }

    private static <T extends CustomPacketPayload> void registerC2S(
            CustomPacketPayload.Type<T> type,
            StreamCodec<RegistryFriendlyByteBuf, T> codec,
            NetworkManager.NetworkReceiver<T> receiver
    ) {
        NetworkManager.registerReceiver(NetworkManager.c2s(), type, codec, receiver);
    }

    private static <T extends CustomPacketPayload> void registerS2C(
            CustomPacketPayload.Type<T> type,
            StreamCodec<? super RegistryFriendlyByteBuf, T> codec,
            NetworkManager.NetworkReceiver<T> receiver
    ) {
        if (Platform.getEnvironment() == Env.CLIENT) {
            NetworkManager.registerReceiver(NetworkManager.s2c(), type, codec, receiver);
        } else {
            NetworkManager.registerS2CPayloadType(type, codec);
        }
    }
}