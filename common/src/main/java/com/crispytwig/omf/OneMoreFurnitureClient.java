package com.crispytwig.omf;

import com.crispytwig.omf.block.entity.MailboxBlockEntity;
import com.crispytwig.omf.client.ChimeModel;
import com.crispytwig.omf.client.FanModel;
import com.crispytwig.omf.client.gui.screens.DrawerScreen;
import com.crispytwig.omf.client.gui.screens.MailboxScreen;
import com.crispytwig.omf.client.renderer.SeatRenderer;
import com.crispytwig.omf.client.renderer.ShelfRenderer;
import com.crispytwig.omf.client.renderer.blockentity.ChimeBlockEntityRenderer;
import com.crispytwig.omf.client.renderer.blockentity.FanBlockEntityRenderer;
import com.crispytwig.omf.client.renderer.blockentity.FlowerBasketRenderer;
import com.crispytwig.omf.registry.OMFBlockEntities;
import com.crispytwig.omf.registry.OMFBlocks;
import com.crispytwig.omf.registry.OMFEntities;
import com.crispytwig.omf.registry.OMFMenus;
import dev.architectury.networking.NetworkManager;
import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import dev.architectury.registry.menu.MenuRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;

@Environment(EnvType.CLIENT)
public class OneMoreFurnitureClient {

    public static void init() {
        EntityRendererRegistry.register(OMFEntities.SEAT, SeatRenderer::new);

        BlockEntityRendererRegistry.register(OMFBlockEntities.SHELF.get(), ShelfRenderer::new);
        BlockEntityRendererRegistry.register(OMFBlockEntities.FLOWER_BASKET.get(), FlowerBasketRenderer::new);
        BlockEntityRendererRegistry.register(OMFBlockEntities.FAN.get(), FanBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(OMFBlockEntities.CHIME.get(), ChimeBlockEntityRenderer::new);


        EntityModelLayerRegistry.register(FanModel.LAYER_LOCATION, FanModel::createBodyLayer);
        EntityModelLayerRegistry.register(ChimeModel.LAYER_LOCATION, ChimeModel::createBodyLayer);

        MenuRegistry.registerScreenFactory(OMFMenus.DRAWER.get(), DrawerScreen::new);
        MenuRegistry.registerScreenFactory(OMFMenus.GENERIC_1X5.get(), MailboxScreen::new);

        NetworkManager.registerReceiver(NetworkManager.Side.S2C, MailboxBlockEntity.packetChannel, ((buf, context) -> {
            var pos = buf.readBlockPos();
            var state = buf.readBoolean();

            var client = Minecraft.getInstance();
            client.execute(() -> {
                var be = client.level.getBlockEntity(pos);
                if (be instanceof MailboxBlockEntity mailbox) {
                    mailbox.failedToSend = state;
                    mailbox.setChanged();
                }
            });


        }));

        NetworkManager.registerReceiver(NetworkManager.Side.S2C, MailboxBlockEntity.packetChannel2, ((buf, context) -> {
            var pos = buf.readBlockPos();
            var state = buf.readBoolean();
            var client = Minecraft.getInstance();
            client.execute(() -> {
                var be = client.level.getBlockEntity(pos);
                if (be instanceof MailboxBlockEntity mailbox) {
                    mailbox.failedToSend = state;
                    mailbox.setChanged();
                }
            });
        }));

        OMFBlocks.LAMPS.forEach((key, value) -> RenderTypeRegistry.register(RenderType.translucent(), value.get()));
        OMFBlocks.MAIL_BOXES.forEach((key, value) -> RenderTypeRegistry.register(RenderType.cutout(), value.get()));
        OMFBlocks.FANS.forEach((key, value) -> RenderTypeRegistry.register(RenderType.translucent(), value.get()));
        OMFBlocks.CURTAINS.forEach((key, value) -> RenderTypeRegistry.register(RenderType.cutout(), value.get()));


        OMFBlocks.BENCHES.forEach((key, value) -> RenderTypeRegistry.register(RenderType.cutout(), value.get()));
        OMFBlocks.FLOWER_BASKETS.forEach((key, value) -> RenderTypeRegistry.register(RenderType.cutout(), value.get()));
        OMFBlocks.SHELVES.forEach((key, value) -> RenderTypeRegistry.register(RenderType.cutout(), value.get()));
        OMFBlocks.TABLES.forEach((key, value) -> RenderTypeRegistry.register(RenderType.cutout(), value.get()));
        OMFBlocks.CHAIRS.forEach((key, value) -> RenderTypeRegistry.register(RenderType.cutout(), value.get()));

        RenderTypeRegistry.register(RenderType.cutout(), OMFBlocks.SPIGOT.get());
        RenderTypeRegistry.register(RenderType.cutout(), OMFBlocks.AMETHYST_WIND_CHIMES.get());
        RenderTypeRegistry.register(RenderType.cutout(), OMFBlocks.BAMBOO_WIND_CHIMES.get());
        RenderTypeRegistry.register(RenderType.cutout(), OMFBlocks.BAMBOO_STRIPPED_WIND_CHIMES.get());
        RenderTypeRegistry.register(RenderType.cutout(), OMFBlocks.BONE_WIND_CHIMES.get());
        RenderTypeRegistry.register(RenderType.cutout(), OMFBlocks.COPPER_WIND_CHIMES.get());
        RenderTypeRegistry.register(RenderType.cutout(), OMFBlocks.ECHO_SHARD_WIND_CHIMES.get());

    }
}
