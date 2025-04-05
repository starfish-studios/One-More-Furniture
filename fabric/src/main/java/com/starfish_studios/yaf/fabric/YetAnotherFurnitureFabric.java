package com.starfish_studios.yaf.fabric;

import com.starfish_studios.yaf.YetAnotherFurniture;
import com.starfish_studios.yaf.events.ShelfInteractions;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;

public class YetAnotherFurnitureFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        YetAnotherFurniture.init();

        UseBlockCallback.EVENT.register(((player, world, hand, hitResult) ->
                ShelfInteractions.interact(player, hand, hitResult)
        ));
    }
}
