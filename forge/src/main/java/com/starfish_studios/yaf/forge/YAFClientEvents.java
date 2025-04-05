package com.starfish_studios.yaf.forge;

import com.starfish_studios.yaf.YetAnotherFurniture;
import com.starfish_studios.yaf.client.ChimeModel;
import com.starfish_studios.yaf.client.FanModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = YetAnotherFurniture.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class YAFClientEvents {

    @SubscribeEvent
    public static void registerModels(final EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(FanModel.LAYER_LOCATION, FanModel::createBodyLayer);
        event.registerLayerDefinition(ChimeModel.LAYER_LOCATION, ChimeModel::createBodyLayer);
    }
}
