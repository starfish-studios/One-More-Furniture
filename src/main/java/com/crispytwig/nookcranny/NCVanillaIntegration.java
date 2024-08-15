package com.crispytwig.nookcranny;

import com.crispytwig.nookcranny.client.renderer.SeatRenderer;
import com.crispytwig.nookcranny.registry.NCEntities;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
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
        }

        private static void registerItemModelPredicates() {
        }

        private static void registerScreens() {
        }

        private static void registerBlockRenderLayers() {
//            BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(),
//            );
        }

        private static void registerParticles() {
//            ParticleFactoryRegistry.getInstance().register(NCParticles.LEAVES, LeafParticle.Provider::new);
        }

        public static <T extends Entity> void registerEntityRenderers(Supplier<EntityType<T>> type, EntityRendererProvider<T> renderProvider) {
            EntityRendererRegistry.register(type.get(), renderProvider);
        }
    }
}
