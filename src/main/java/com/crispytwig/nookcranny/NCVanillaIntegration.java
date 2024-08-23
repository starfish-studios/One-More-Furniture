package com.crispytwig.nookcranny;

import com.crispytwig.nookcranny.client.gui.screens.MailboxScreen;
import com.crispytwig.nookcranny.client.renderer.SeatRenderer;
import com.crispytwig.nookcranny.client.renderer.ShelfRenderer;
import com.crispytwig.nookcranny.registry.NCBlockEntities;
import com.crispytwig.nookcranny.registry.NCBlocks;
import com.crispytwig.nookcranny.registry.NCEntities;
import com.crispytwig.nookcranny.registry.NCMenus;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

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
        }

        private static void registerRenderers() {
            registerEntityRenderers(NCEntities.SEAT, SeatRenderer::new);
            BlockEntityRendererRegistry.register(NCBlockEntities.SHELF, ShelfRenderer::new);

        }

        private static void registerItemModelPredicates() {
        }

        private static void registerScreens() {
            MenuScreens.register(NCMenus.GENERIC_1X5, MailboxScreen::new);
        }

        private static void registerBlockRenderLayers() {
            BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(),
                    NCBlocks.SPIGOT,
                    NCBlocks.OAK_MAILBOX,
//                    NCBlocks.PLATE,

                    NCBlocks.OAK_SHELF,
                    NCBlocks.SPRUCE_SHELF,
                    NCBlocks.BIRCH_SHELF,
                    NCBlocks.JUNGLE_SHELF,
                    NCBlocks.ACACIA_SHELF,
                    NCBlocks.CHERRY_SHELF,
                    NCBlocks.DARK_OAK_SHELF,
                    NCBlocks.MANGROVE_SHELF,
                    NCBlocks.BAMBOO_SHELF,

                    NCBlocks.OAK_TABLE,
                    NCBlocks.SPRUCE_TABLE,
                    NCBlocks.BIRCH_TABLE,
                    NCBlocks.JUNGLE_TABLE,
                    NCBlocks.ACACIA_TABLE,
                    NCBlocks.CHERRY_TABLE,
                    NCBlocks.DARK_OAK_TABLE,
                    NCBlocks.MANGROVE_TABLE,
                    NCBlocks.BAMBOO_TABLE,

                    NCBlocks.OAK_CHAIR,
                    NCBlocks.SPRUCE_CHAIR,
                    NCBlocks.BIRCH_CHAIR,
                    NCBlocks.JUNGLE_CHAIR,
                    NCBlocks.ACACIA_CHAIR,
                    NCBlocks.CHERRY_CHAIR,
                    NCBlocks.DARK_OAK_CHAIR,
                    NCBlocks.MANGROVE_CHAIR,
                    NCBlocks.BAMBOO_CHAIR
            );
        }

        private static void registerParticles() {
        }

        public static <T extends Entity> void registerEntityRenderers(Supplier<EntityType<T>> type, EntityRendererProvider<T> renderProvider) {
            EntityRendererRegistry.register(type.get(), renderProvider);
        }
    }
}
