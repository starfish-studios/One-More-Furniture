package com.starfish_studios.yaf;

import com.google.common.collect.Lists;
import eu.midnightdust.lib.config.MidnightConfig;

import java.util.List;

public class YAFConfig extends MidnightConfig {

    public static final String CLIENT = "client";
    public static final String SERVER = "server";

    public enum stackCountMode {
        NEVER, CROUCH, ALWAYS
    }
    @Comment(category = CLIENT) public static Comment clientComment;

    @Entry(category = CLIENT) public static boolean stillItems = false;
    @Entry(category = CLIENT) public static stackCountMode shelfNumberTag = stackCountMode.CROUCH;




    @Entry(category = SERVER) public static List<String> flowerBasketItems =
            Lists.newArrayList("#minecraft:flowers", "#minecraft:leaves");
}
