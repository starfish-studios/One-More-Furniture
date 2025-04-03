package com.crispytwig.omf.forge;

import com.crispytwig.omf.OneMoreFurniture;
import com.crispytwig.omf.OneMoreFurnitureClient;
import com.crispytwig.omf.events.ShelfInteractions;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(OneMoreFurniture.MOD_ID)
public class OneMoreFurnitureForge {
    public OneMoreFurnitureForge(FMLJavaModLoadingContext ctx) {

        EventBuses.registerModEventBus(OneMoreFurniture.MOD_ID, ctx.getModEventBus());
        OneMoreFurniture.init();
    }

    @SubscribeEvent
    public static void registerBlockRenderType(final FMLClientSetupEvent event) {
        event.enqueueWork(OneMoreFurnitureClient::init);
    }

    @SubscribeEvent
    public static void registerBlockInteract(final PlayerInteractEvent.RightClickBlock event) {
        var result = ShelfInteractions.interact(event.getEntity(), event.getHand(), event.getHitVec());
        if (result.consumesAction()) {
            event.setCanceled(true);
        }
    }
}
