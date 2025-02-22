package com.crispytwig.nookcranny;

import eu.midnightdust.lib.config.MidnightConfig;

public class NCConfig extends MidnightConfig {
    @Entry(category = "client") public static boolean stillItems = false;
    @Entry(category = "client") public static boolean constantStackCount = false;
    @Entry(category = "client") public static boolean noStackCount = false;
}
