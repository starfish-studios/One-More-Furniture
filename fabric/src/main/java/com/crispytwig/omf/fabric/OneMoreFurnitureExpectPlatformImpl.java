package com.crispytwig.omf.fabric;

import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class OneMoreFurnitureExpectPlatformImpl {

    public static Path getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir();
    }
}
