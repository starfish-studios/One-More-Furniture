package com.crispytwig.omf.forge;

import com.crispytwig.omf.OneMoreFurniture;
import com.crispytwig.omf.client.ChimeModel;
import com.crispytwig.omf.client.FanModel;
import com.crispytwig.omf.events.ShelfInteractions;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = OneMoreFurniture.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OMFClientEvents {

    @SubscribeEvent
    public static void registerModels(final EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(FanModel.LAYER_LOCATION, FanModel::createBodyLayer);
        event.registerLayerDefinition(ChimeModel.LAYER_LOCATION, ChimeModel::createBodyLayer);
    }
}
