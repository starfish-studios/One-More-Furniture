package com.crispytwig.omf;

import com.google.common.collect.Lists;
import eu.midnightdust.lib.config.MidnightConfig;

import java.util.List;

public class OMFConfig extends MidnightConfig {
    @Entry(category = "client") public static boolean stillItems = false;
    @Entry(category = "client") public static boolean constantStackCount = false;
    @Entry(category = "client") public static boolean noStackCount = false;
    @Entry(category = "main") public static List<String> flowerBoxItems =
            Lists.newArrayList("#minecraft:flowers", "#minecraft:leaves");
}
