package com.crispytwig.nookcranny.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class NCDataGenerator implements DataGeneratorEntrypoint  {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {

        var pack = fabricDataGenerator.createPack();

        pack.addProvider(NCModelProvider::new);
        pack.addProvider(NCLangProvider::new);
    }
}