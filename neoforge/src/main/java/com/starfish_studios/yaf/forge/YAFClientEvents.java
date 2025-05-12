package com.starfish_studios.yaf.forge;

import com.starfish_studios.yaf.YetAnotherFurniture;
import com.starfish_studios.yaf.client.model.*;
import com.starfish_studios.yaf.client.renderer.SeatRenderer;
import com.starfish_studios.yaf.registry.YAFEntities;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(value = Dist.CLIENT, modid = YetAnotherFurniture.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class YAFClientEvents {

    @SubscribeEvent
    public static void registerModels(final EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(FanBlockEntityModel.LAYER_LOCATION, FanBlockEntityModel::createBodyLayer);
        event.registerLayerDefinition(ChimeBlockEntityModel.LAYER_LOCATION, ChimeBlockEntityModel::createBodyLayer);
        event.registerLayerDefinition(TableBlockEntityModel.LAYER_LOCATION, TableBlockEntityModel::createBodyLayer);
        event.registerLayerDefinition(TableclothModel.LAYER_LOCATION, TableclothModel::createBodyLayer);
        event.registerLayerDefinition(ChairBlockEntityModel.LAYER_LOCATION, ChairBlockEntityModel::createBodyLayer);
        event.registerLayerDefinition(ChairCushionModel.LAYER_LOCATION, ChairCushionModel::createBodyLayer);
        event.registerLayerDefinition(TallStoolBlockEntityModel.LAYER_LOCATION, TallStoolBlockEntityModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerEntityrtender(final EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(YAFEntities.SEAT.get(), SeatRenderer::new);
    }
}
