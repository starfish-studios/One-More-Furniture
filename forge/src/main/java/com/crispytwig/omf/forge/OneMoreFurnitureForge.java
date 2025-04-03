package com.crispytwig.omf.forge;

import com.crispytwig.omf.OneMoreFurniture;
import com.crispytwig.omf.OneMoreFurnitureClient;
import com.crispytwig.omf.client.gui.screens.DrawerScreen;
import com.crispytwig.omf.client.gui.screens.MailboxScreen;
import com.crispytwig.omf.events.ShelfInteractions;
import com.crispytwig.omf.registry.OMFMenus;
import dev.architectury.platform.forge.EventBuses;
import dev.architectury.registry.menu.MenuRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(OneMoreFurniture.MOD_ID)
public class OneMoreFurnitureForge {
    public OneMoreFurnitureForge(FMLJavaModLoadingContext ctx) {

        var bus = ctx.getModEventBus();
        EventBuses.registerModEventBus(OneMoreFurniture.MOD_ID,bus);
        OneMoreFurniture.init();

        if (FMLEnvironment.dist == Dist.CLIENT) {
            bus.addListener(OneMoreFurnitureForge::onClientSetup);
        }
    }

    @SubscribeEvent
    public static void onClientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(OneMoreFurnitureClient::init);
        event.enqueueWork(() -> {
            MenuScreens.register(OMFMenus.DRAWER.get(), DrawerScreen::new);
            MenuScreens.register(OMFMenus.GENERIC_1X5.get(), MailboxScreen::new);
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
