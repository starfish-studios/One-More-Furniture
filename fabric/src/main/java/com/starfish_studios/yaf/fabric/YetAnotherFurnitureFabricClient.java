package com.starfish_studios.yaf.fabric;

import com.starfish_studios.yaf.YetAnotherFurnitureClient;
import net.fabricmc.api.ClientModInitializer;

public class YetAnotherFurnitureFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        YetAnotherFurnitureClient.init();
    }
}
