package com.starfish_studios.yaf.forge;


import net.neoforged.fml.loading.FMLPaths;

import java.nio.file.Path;

public class YAFExpectPlatformImpl {

    public static Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }
}
