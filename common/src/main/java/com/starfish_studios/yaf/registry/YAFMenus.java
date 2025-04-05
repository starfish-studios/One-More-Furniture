package com.starfish_studios.yaf.registry;

import com.starfish_studios.yaf.YetAnotherFurniture;
import com.starfish_studios.yaf.inventory.DrawerMenu;
import com.starfish_studios.yaf.inventory.MailboxMenu;
import dev.architectury.registry.menu.MenuRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;

public class YAFMenus {

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(YetAnotherFurniture.MOD_ID, Registries.MENU);

    public static final RegistrySupplier<MenuType<MailboxMenu>> GENERIC_1X5 =
            MENUS.register("mailbox", () -> MenuRegistry.ofExtended(MailboxMenu::new));

    public static final RegistrySupplier<MenuType<DrawerMenu>> DRAWER =
            MENUS.register("drawer", () ->  MenuRegistry.ofExtended(DrawerMenu::new));


}
