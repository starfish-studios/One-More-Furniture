package com.crispytwig.omf.forge;

import com.crispytwig.omf.OneMoreFurniture;
import com.crispytwig.omf.OneMoreFurnitureClient;
import com.crispytwig.omf.client.ChimeModel;
import com.crispytwig.omf.client.FanModel;
import com.crispytwig.omf.client.gui.screens.DrawerScreen;
import com.crispytwig.omf.client.gui.screens.MailboxScreen;
import com.crispytwig.omf.events.ShelfInteractions;
import com.crispytwig.omf.registry.OMFMenus;
import dev.architectury.platform.forge.EventBuses;
import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

import static net.minecraftforge.fml.DistExecutor.runForDist;

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
    }

    @SubscribeEvent
    public static void registerBlockInteract(final PlayerInteractEvent.RightClickBlock event) {
        var result = ShelfInteractions.interact(event.getEntity(), event.getHand(), event.getHitVec());
        if (result.consumesAction()) {
            event.setCanceled(true);
        }
    }
}
