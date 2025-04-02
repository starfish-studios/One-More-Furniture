package com.crispytwig.nookcranny;

import com.crispytwig.nookcranny.blocks.CurtainBlock;
import com.crispytwig.nookcranny.blocks.FanBlock;
import com.crispytwig.nookcranny.blocks.LampBlock;
import com.crispytwig.nookcranny.blocks.MailboxBlock;
import com.crispytwig.nookcranny.client.ChimeModel;
import com.crispytwig.nookcranny.client.FanModel;
import com.crispytwig.nookcranny.client.gui.screens.DrawerScreen;
import com.crispytwig.nookcranny.client.gui.screens.MailboxScreen;
import com.crispytwig.nookcranny.client.renderer.SeatRenderer;
import com.crispytwig.nookcranny.client.renderer.ShelfRenderer;
import com.crispytwig.nookcranny.client.renderer.blockentity.ChimeBlockEntityRenderer;
import com.crispytwig.nookcranny.client.renderer.blockentity.FanBlockEntityRenderer;
import com.crispytwig.nookcranny.client.renderer.blockentity.FlowerBasketRenderer;
import com.crispytwig.nookcranny.registry.NCBlockEntities;
import com.crispytwig.nookcranny.registry.NCBlocks;
import com.crispytwig.nookcranny.registry.NCEntities;
import com.crispytwig.nookcranny.registry.NCMenus;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.DyeColor;

import java.util.function.Supplier;

public class NCVanillaIntegration {

    public static void serverInit() {
        addResourcePacks();
    }

    public static void addResourcePacks() {
        ModContainer modContainer = FabricLoader.getInstance().getModContainer(NookAndCranny.MOD_ID).orElseThrow(() -> new IllegalStateException("Nook and Cranny's ModContainer couldn't be found!"));
    }

    @Environment(EnvType.CLIENT)
    public static class Client {

        public static void clientInit() {
            registerRenderers();
            registerScreens();
            registerBlockRenderLayers();
            registerItemModelPredicates();
            registerParticles();

            EntityModelLayerRegistry.registerModelLayer(FanModel.LAYER_LOCATION, FanModel::createBodyLayer);
            EntityModelLayerRegistry.registerModelLayer(ChimeModel.LAYER_LOCATION, ChimeModel::createBodyLayer);

            /*
            ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
                    return DyeColor.RED.getFireworkColor();
                    }
            , NCBlocks.WHITE_CURTAIN);
             */
        }

        private static void registerRenderers() {
            registerEntityRenderers(NCEntities.SEAT, SeatRenderer::new);
            BlockEntityRendererRegistry.register(NCBlockEntities.SHELF, ShelfRenderer::new);
            BlockEntityRendererRegistry.register(NCBlockEntities.FLOWER_BASKET, FlowerBasketRenderer::new);
            BlockEntityRendererRegistry.register(NCBlockEntities.FAN, FanBlockEntityRenderer::new);
            BlockEntityRendererRegistry.register(NCBlockEntities.CHIME, ChimeBlockEntityRenderer::new);

        }

        private static void registerItemModelPredicates() {
        }

        private static void registerScreens() {
            MenuScreens.register(NCMenus.GENERIC_1X5, MailboxScreen::new);
            MenuScreens.register(NCMenus.DRAWER, DrawerScreen::new);
        }

        private static void registerBlockRenderLayers() {
            NCBlocks.BLOCKS.stream().filter(block -> block instanceof LampBlock).forEach(lamp -> BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.translucent(), lamp));
            NCBlocks.BLOCKS.stream().filter(block -> block instanceof MailboxBlock).forEach(mailbox -> BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), mailbox));
            NCBlocks.BLOCKS.stream().filter(block -> block instanceof FanBlock).forEach(lamp -> BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.translucent(), lamp));
            NCBlocks.BLOCKS.stream().filter(block -> block instanceof CurtainBlock).forEach(lamp -> BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), lamp));



            BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(),
                    NCBlocks.SPIGOT,
//                    NCBlocks.PLATE,

                    NCBlocks.OAK_BENCH,
                    NCBlocks.SPRUCE_BENCH,
                    NCBlocks.BIRCH_BENCH,
                    NCBlocks.JUNGLE_BENCH,
                    NCBlocks.ACACIA_BENCH,
                    NCBlocks.CHERRY_BENCH,
                    NCBlocks.DARK_OAK_BENCH,
                    NCBlocks.MANGROVE_BENCH,
                    NCBlocks.BAMBOO_BENCH,
                    NCBlocks.CRIMSON_BENCH,
                    NCBlocks.WARPED_BENCH,

                    NCBlocks.OAK_FLOWER_BASKET,

                    NCBlocks.OAK_SHELF,
                    NCBlocks.SPRUCE_SHELF,
                    NCBlocks.BIRCH_SHELF,
                    NCBlocks.JUNGLE_SHELF,
                    NCBlocks.ACACIA_SHELF,
                    NCBlocks.CHERRY_SHELF,
                    NCBlocks.DARK_OAK_SHELF,
                    NCBlocks.MANGROVE_SHELF,
                    NCBlocks.BAMBOO_SHELF,
                    NCBlocks.CRIMSON_SHELF,
                    NCBlocks.WARPED_SHELF,

                    NCBlocks.OAK_TABLE,
                    NCBlocks.SPRUCE_TABLE,
                    NCBlocks.BIRCH_TABLE,
                    NCBlocks.JUNGLE_TABLE,
                    NCBlocks.ACACIA_TABLE,
                    NCBlocks.CHERRY_TABLE,
                    NCBlocks.DARK_OAK_TABLE,
                    NCBlocks.MANGROVE_TABLE,
                    NCBlocks.BAMBOO_TABLE,
                    NCBlocks.CRIMSON_TABLE,
                    NCBlocks.WARPED_TABLE,

                    NCBlocks.OAK_CHAIR,
                    NCBlocks.SPRUCE_CHAIR,
                    NCBlocks.BIRCH_CHAIR,
                    NCBlocks.JUNGLE_CHAIR,
                    NCBlocks.ACACIA_CHAIR,
                    NCBlocks.CHERRY_CHAIR,
                    NCBlocks.DARK_OAK_CHAIR,
                    NCBlocks.MANGROVE_CHAIR,
                    NCBlocks.BAMBOO_CHAIR,
                    NCBlocks.CRIMSON_CHAIR,
                    NCBlocks.WARPED_CHAIR,

                    NCBlocks.AMETHYST_WIND_CHIMES,
                    NCBlocks.BAMBOO_WIND_CHIMES,
                    NCBlocks.BAMBOO_STRIPPED_WIND_CHIMES,
                    NCBlocks.BONE_WIND_CHIMES,
                    NCBlocks.COPPER_WIND_CHIMES,
                    NCBlocks.ECHO_SHARD_WIND_CHIMES
            );
        }

        private static void registerParticles() {
        }

        public static <T extends Entity> void registerEntityRenderers(Supplier<EntityType<T>> type, EntityRendererProvider<T> renderProvider) {
            EntityRendererRegistry.register(type.get(), renderProvider);
        }
    }
}
