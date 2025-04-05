package com.starfish_studios.yaf;

import dev.architectury.injectables.annotations.ExpectPlatform;

import java.nio.file.Path;

public class YetAnotherFurnitureExpectPlatform {

    @ExpectPlatform
    public static Path getConfigDirectory() {
        throw new AssertionError();
    }
}
