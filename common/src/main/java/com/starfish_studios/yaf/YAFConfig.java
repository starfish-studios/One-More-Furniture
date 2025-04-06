package com.starfish_studios.yaf;

import com.google.common.collect.Lists;
import eu.midnightdust.lib.config.MidnightConfig;

import java.util.List;

public class YAFConfig extends MidnightConfig {

    public static final String CLIENT = "client";
    public static final String SERVER = "server";

    @Comment(category = CLIENT) public static Comment clientComment;

    @Entry(category = CLIENT) public static boolean stillItems = false;


//    @Entry(category = CLIENT) public static StackCountMode shelfNumberTag = StackCountMode.CROUCH;
//    public enum StackCountMode {
//        NEVER, CROUCH, ALWAYS;
//    }

    @Entry(category = CLIENT) public static boolean neverShowShelfNumbers = false;
    @Entry(category = CLIENT) public static boolean alwaysShowShelfNumbers = false;

    @Comment(category = SERVER) public static Comment serverComment;

    @Entry(category = SERVER) public static List<String> flowerBasketItems =
            Lists.newArrayList("#minecraft:flowers", "#minecraft:leaves");
}
