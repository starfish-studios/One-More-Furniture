package com.starfish_studios.yaf.forge;

import com.starfish_studios.yaf.YetAnotherFurniture;
import com.starfish_studios.yaf.YetAnotherFurnitureClient;
import com.starfish_studios.yaf.client.gui.screens.DrawerScreen;
import com.starfish_studios.yaf.client.gui.screens.MailboxScreen;
import com.starfish_studios.yaf.events.ShelfInteractions;
import com.starfish_studios.yaf.registry.YAFMenus;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.Map;

@Mod(YetAnotherFurniture.MOD_ID)
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class YetAnotherFurnitureForge {

    private static final boolean runBlockStateTest = false;

    public YetAnotherFurnitureForge() {

        var bus = NeoForge.EVENT_BUS;
        YetAnotherFurniture.init();

        if (FMLEnvironment.dist == Dist.CLIENT) {
            bus.addListener(YetAnotherFurnitureForge::onClientSetup);
        }
        bus.addListener(YetAnotherFurnitureForge::onCommonSetup);
    }

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        if (runBlockStateTest) {
            event.enqueueWork(() -> {
                Map<String, Integer> stateCounts = new HashMap<>();
                int totalStates = 0;

                for (Block block : BuiltInRegistries.BLOCK) {
                    ResourceLocation id = BuiltInRegistries.BLOCK.getKey(block);
                    if (id.getNamespace().equals(YetAnotherFurniture.MOD_ID)) {
                        String className = block.getClass().getSimpleName();
                        int count = block.getStateDefinition().getPossibleStates().size();

                        stateCounts.merge(className, count, Integer::sum);
                        totalStates += count;
                    }
                }

                stateCounts.forEach((type, count) ->
                        System.out.println("  " + type + ": " + count + " states"));

                System.out.println("[YAF] Total BlockStates registered: " + totalStates);
            });
        }
    }

    @SubscribeEvent
    public static void registerScreens(final RegisterMenuScreensEvent event) {
        event.register(YAFMenus.DRAWER.get(), DrawerScreen::new);
        event.register(YAFMenus.GENERIC_1X5.get(), MailboxScreen::new);
    }

    @SubscribeEvent
    public static void onClientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(YetAnotherFurnitureClient::init);

    }

    @SubscribeEvent
    public static void registerBlockInteract(final PlayerInteractEvent.RightClickBlock event) {
        var result = ShelfInteractions.interact(event.getEntity(), event.getHand(), event.getHitVec());
        if (result.consumesAction()) {
            event.setCanceled(true);
        }
    }
}
