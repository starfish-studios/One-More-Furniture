package com.crispytwig.nookcranny.registry;

import com.crispytwig.nookcranny.NookAndCranny;
import com.crispytwig.nookcranny.inventory.DrawerMenu;
import com.crispytwig.nookcranny.inventory.MailboxMenu;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.fabricmc.fabric.api.screenhandler.v1.FabricScreenHandlerFactory;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.DispenserMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.Block;

public class NCMenus {

    public static final MenuType<MailboxMenu> GENERIC_1X5 = registerExtended("mailbox", new ExtendedScreenHandlerType(MailboxMenu::new));

    public static final MenuType<DrawerMenu> DRAWER = registerExtended("drawer", new ExtendedScreenHandlerType(DrawerMenu::new));

    private static <T extends AbstractContainerMenu> MenuType registerExtended(String id, ExtendedScreenHandlerType menuSupplier) {
        return Registry.register(BuiltInRegistries.MENU, new ResourceLocation(NookAndCranny.MOD_ID, id), menuSupplier);
    }
}
