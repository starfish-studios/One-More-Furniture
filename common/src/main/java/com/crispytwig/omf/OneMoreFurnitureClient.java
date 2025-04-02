package com.crispytwig.omf;

import com.crispytwig.omf.client.gui.screens.DrawerScreen;
import com.crispytwig.omf.client.gui.screens.MailboxScreen;
import com.crispytwig.omf.inventory.DrawerMenu;
import com.crispytwig.omf.inventory.MailboxMenu;
import com.crispytwig.omf.registry.OMFMenus;
import dev.architectury.registry.menu.MenuRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class OneMoreFurnitureClient {

    public static void init() {
        MenuRegistry.registerScreenFactory(OMFMenus.DRAWER.get(), DrawerScreen::new);
        MenuRegistry.registerScreenFactory(OMFMenus.GENERIC_1X5.get(), MailboxScreen::new);


    }
}
