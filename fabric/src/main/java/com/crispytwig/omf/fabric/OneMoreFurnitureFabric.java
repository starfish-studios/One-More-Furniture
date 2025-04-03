package com.crispytwig.omf.fabric;

import com.crispytwig.omf.OneMoreFurniture;
import com.crispytwig.omf.events.ShelfInteractions;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;

public class OneMoreFurnitureFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        OneMoreFurniture.init();

        UseBlockCallback.EVENT.register(((player, world, hand, hitResult) ->
                ShelfInteractions.interact(player, hand, hitResult)
        ));
    }
}
