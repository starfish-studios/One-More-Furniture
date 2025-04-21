package com.starfish_studios.yaf.forge;

import com.starfish_studios.yaf.YetAnotherFurniture;
import com.starfish_studios.yaf.client.model.ChimeBlockEntityModel;
import com.starfish_studios.yaf.client.model.FanBlockEntityModel;
import com.starfish_studios.yaf.client.model.TableBlockEntityModel;
import com.starfish_studios.yaf.client.model.TableclothModel;
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
    }
}
