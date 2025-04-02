package com.crispytwig.omf.fabric;

import com.crispytwig.omf.OneMoreFurniture;
import net.fabricmc.api.ModInitializer;

public class OneMoreFurnitureFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        OneMoreFurniture.init();
    }
}
