package com.starfish_studios.yaf.fabric;

import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class YAFExpectPlatformImpl {

    public static Path getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir();
    }
}
