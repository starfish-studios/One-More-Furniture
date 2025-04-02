package com.crispytwig.omf.fabric;

import com.crispytwig.omf.OneMoreFurnitureClient;
import net.fabricmc.api.ClientModInitializer;

public class OneMoreFurnitureFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        OneMoreFurnitureClient.init();
    }
}
