package com.crispytwig.omf;

import eu.midnightdust.lib.config.MidnightConfig;

public class OMFConfig extends MidnightConfig {
    @Entry(category = "client") public static boolean stillItems = false;
    @Entry(category = "client") public static boolean constantStackCount = false;
    @Entry(category = "client") public static boolean noStackCount = false;
}
