package com.starfish_studios.yaf.forge;

import com.starfish_studios.yaf.YetAnotherFurniture;
import com.starfish_studios.yaf.client.model.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = YetAnotherFurniture.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class YAFClientEvents {

    @SubscribeEvent
    public static void registerModels(final EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(FanBlockEntityModel.LAYER_LOCATION, FanBlockEntityModel::createBodyLayer);
        event.registerLayerDefinition(ChimeBlockEntityModel.LAYER_LOCATION, ChimeBlockEntityModel::createBodyLayer);
        event.registerLayerDefinition(TableBlockEntityModel.LAYER_LOCATION, TableBlockEntityModel::createBodyLayer);
        event.registerLayerDefinition(TableclothModel.LAYER_LOCATION, TableclothModel::createBodyLayer);
        event.registerLayerDefinition(ChairBlockEntityModel.LAYER_LOCATION, ChairBlockEntityModel::createBodyLayer);
        event.registerLayerDefinition(ChairCushionModel.LAYER_LOCATION, ChairCushionModel::createBodyLayer);
    }
}
