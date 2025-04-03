package com.crispytwig.omf;

import com.crispytwig.omf.block.entity.MailboxBlockEntity;
import com.crispytwig.omf.client.gui.screens.widget.LockTargetMailboxWidget;
import com.crispytwig.omf.events.*;
import com.crispytwig.omf.inventory.MailboxMenu;
import com.crispytwig.omf.registry.*;
import com.google.common.base.Suppliers;
import dev.architectury.event.events.common.InteractionEvent;
import dev.architectury.networking.NetworkManager;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrarManager;
import dev.architectury.registry.registries.RegistrySupplier;
import eu.midnightdust.lib.config.MidnightConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.function.Supplier;

public class OneMoreFurniture {
    public static final String MOD_ID = "omf";
    public static final Supplier<RegistrarManager> REGISTRIES = Suppliers.memoize(() -> RegistrarManager.get(MOD_ID));

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static final RegistrySupplier<CreativeModeTab> MAIN = TABS.register("main",
            () -> CreativeTabRegistry.create(builder -> {
                builder.title(Component.translatable("itemGroup.nookcranny.tab"));
                builder.icon(() -> new ItemStack(OMFItems.BENCHES.get(OMFWoodType.OAK).get()));
                builder.displayItems((params, output) -> {
                    OMFItems.ITEMS.forEach(itemRegistrySupplier ->
                            output.accept(itemRegistrySupplier.get().getDefaultInstance()));
                });
            })
    );


    public static void init() {
        MidnightConfig.init(MOD_ID, OMFConfig.class);

        TABS.register();
        OMFSoundEvents.SOUNDS.register();
        OMFBlocks.BLOCKS.register();
        OMFItems.ITEMS.register();
        OMFBlockEntities.BLOCK_ENTITY_TYPES.register();
        OMFMenus.MENUS.register();
        OMFEntities.ENTITY_TYPES.register();

        InteractionEvent.RIGHT_CLICK_BLOCK.register(ChairInteractions::interact);
        InteractionEvent.RIGHT_CLICK_BLOCK.register(CushionableEvents::interact);
        InteractionEvent.RIGHT_CLICK_BLOCK.register(DyeSofa::interact);
        InteractionEvent.RIGHT_CLICK_BLOCK.register(LampInteractions::interact);
        InteractionEvent.RIGHT_CLICK_BLOCK.register(TableInteractions::interact);

        NetworkManager.registerReceiver(NetworkManager.Side.C2S, MailboxMenu.packetChannel, ((buf, context) -> {
            var name =  buf.readUtf();
            var pos = buf.readBlockPos();
            var player = context.getPlayer();
            var be = player.level().getBlockEntity(pos);
            if (be instanceof MailboxBlockEntity mailboxBlockEntity) {
                mailboxBlockEntity.targetString = name;
                mailboxBlockEntity.setChanged();
            }
        }));


        NetworkManager.registerReceiver(NetworkManager.Side.C2S, LockTargetMailboxWidget.packetChannel, ((buf, context)  -> {
            var posi = buf.readBlockPos();
            var lockTarget = buf.readBoolean();
            var be = context.getPlayer().level().getBlockEntity(posi);
            if (be instanceof MailboxBlockEntity mailboxBlockEntity) {
                mailboxBlockEntity.lockTarget = lockTarget;
                mailboxBlockEntity.setChanged();
            }
        }));
    }
}
