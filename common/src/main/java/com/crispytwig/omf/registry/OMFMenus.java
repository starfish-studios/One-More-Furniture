package com.crispytwig.omf.registry;

import com.crispytwig.omf.OneMoreFurniture;
import com.crispytwig.omf.inventory.DrawerMenu;
import com.crispytwig.omf.inventory.MailboxMenu;
import dev.architectury.registry.menu.MenuRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class OMFMenus {

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(OneMoreFurniture.MOD_ID, Registries.MENU);

    public static final RegistrySupplier<MenuType<MailboxMenu>> GENERIC_1X5 =
            MENUS.register("mailbox", () -> MenuRegistry.ofExtended(MailboxMenu::new));

    public static final RegistrySupplier<MenuType<DrawerMenu>> DRAWER =
            MENUS.register("drawer", () ->  MenuRegistry.ofExtended(DrawerMenu::new));


}
