package com.starfish_studios.yaf.forge;

import com.starfish_studios.yaf.YetAnotherFurniture;
import com.starfish_studios.yaf.YetAnotherFurnitureClient;
import com.starfish_studios.yaf.client.gui.screens.DrawerScreen;
import com.starfish_studios.yaf.client.gui.screens.MailboxScreen;
import com.starfish_studios.yaf.events.ShelfInteractions;
import com.starfish_studios.yaf.registry.YAFMenus;
import dev.architectury.platform.forge.EventBuses;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

@Mod(YetAnotherFurniture.MOD_ID)
public class YetAnotherFurnitureForge {
    public YetAnotherFurnitureForge(FMLJavaModLoadingContext ctx) {

        var bus = ctx.getModEventBus();
        EventBuses.registerModEventBus(YetAnotherFurniture.MOD_ID,bus);
        YetAnotherFurniture.init();

        if (FMLEnvironment.dist == Dist.CLIENT) {
            bus.addListener(YetAnotherFurnitureForge::onClientSetup);
        }
        bus.addListener(YetAnotherFurnitureForge::onCommonSetup);
    }

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Map<String, Integer> stateCounts = new HashMap<>();
            int totalStates = 0;

            for (Block block : ForgeRegistries.BLOCKS) {
                ResourceLocation id = ForgeRegistries.BLOCKS.getKey(block);
                if (id != null && id.getNamespace().equals(YetAnotherFurniture.MOD_ID)) {
                    String className = block.getClass().getSimpleName();
                    int count = block.getStateDefinition().getPossibleStates().size();

                    stateCounts.merge(className, count, Integer::sum);
                    totalStates += count;
                }
            }

            System.out.println("[YAF] BlockState breakdown by block type:");
            stateCounts.forEach((type, count) ->
                    System.out.println("  " + type + ": " + count + " states"));

            System.out.println("[YAF] Total BlockStates registered: " + totalStates);
        });
    }


    @SubscribeEvent
    public static void onClientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(YetAnotherFurnitureClient::init);
        event.enqueueWork(() -> {
            MenuScreens.register(YAFMenus.DRAWER.get(), DrawerScreen::new);
            MenuScreens.register(YAFMenus.GENERIC_1X5.get(), MailboxScreen::new);
        });
    }

    @SubscribeEvent
    public static void registerBlockInteract(final PlayerInteractEvent.RightClickBlock event) {
        var result = ShelfInteractions.interact(event.getEntity(), event.getHand(), event.getHitVec());
        if (result.consumesAction()) {
            event.setCanceled(true);
        }
    }
}
