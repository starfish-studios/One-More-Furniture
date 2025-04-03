package com.crispytwig.omf.fabric.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class OMFDataGenerator implements DataGeneratorEntrypoint  {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {

        var pack = fabricDataGenerator.createPack();

        pack.addProvider(OMFModelProvider::new);
        pack.addProvider(OMFLangProvider::new);
        pack.addProvider(OMFBlockLootTableProvider::new);
        pack.addProvider(OMFBlockTagProvider::new);
        pack.addProvider(OMFRecipeProvider::new);
    }
}