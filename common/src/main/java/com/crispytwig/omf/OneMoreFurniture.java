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
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;

import java.util.Map;
import java.util.function.Supplier;

public class OneMoreFurniture {
    public static final String MOD_ID = "omf";
    public static final Supplier<RegistrarManager> REGISTRIES = Suppliers.memoize(() -> RegistrarManager.get(MOD_ID));

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(MOD_ID, Registries.CREATIVE_MODE_TAB);

    //Moved from Client-side only code so it can be registered on server
    public static ResourceLocation lockTargetId = new ResourceLocation(OneMoreFurniture.MOD_ID, "switch_lock");

    public static final RegistrySupplier<CreativeModeTab> MAIN = TABS.register("main",
            () -> CreativeTabRegistry.create(builder -> {
                builder.title(Component.translatable("itemGroup.omf.tab"));
                builder.icon(() -> new ItemStack(OMFItems.BENCHES.get(OMFWoodType.OAK).get()));
                builder.displayItems((params, output) -> {
                    output.accept(OMFItems.COPPER_SAW.get().getDefaultInstance());

                    addAll(output, OMFItems.SOFAS);
                    addAll(output, OMFItems.CURTAINS);
                    addAll(output, OMFItems.BENCHES);
                    addAll(output, OMFItems.DRAWERS);
                    addAll(output, OMFItems.TALL_STOOLS);
                    addAll(output, OMFItems.FLOWER_BASKETS);
                    addAll(output, OMFItems.LAMPS);
                    addAll(output, OMFItems.MAIL_BOXES);
                    addAll(output, OMFItems.SHELVES);
                    addAll(output, OMFItems.TABLES);
                    addAll(output, OMFItems.CHAIRS);
                    addAll(output, OMFItems.FANS);
                    addAll(output, OMFItems.CABINET);

                    // Add standalone items
                    output.accept(OMFItems.SPIGOT.get().getDefaultInstance());
                    output.accept(OMFItems.AMETHYST_WIND_CHIMES.get().getDefaultInstance());
                    output.accept(OMFItems.BAMBOO_WIND_CHIMES.get().getDefaultInstance());
                    output.accept(OMFItems.BAMBOO_STRIPPED_WIND_CHIMES.get().getDefaultInstance());
                    output.accept(OMFItems.BONE_WIND_CHIMES.get().getDefaultInstance());
                    output.accept(OMFItems.COPPER_WIND_CHIMES.get().getDefaultInstance());
                    output.accept(OMFItems.ECHO_SHARD_WIND_CHIMES.get().getDefaultInstance());
                });
            })
    );

    private static void addAll(CreativeModeTab.Output output, Map<?, RegistrySupplier<BlockItem>> map) {
        map.values().forEach(supplier -> output.accept(supplier.get().getDefaultInstance()));
    }


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
            context.queue(() -> {
                var be = player.level().getBlockEntity(pos);
                if (be instanceof MailboxBlockEntity mailboxBlockEntity) {
                    mailboxBlockEntity.targetString = name;
                    mailboxBlockEntity.setChanged();
                }
            });
        }));


        NetworkManager.registerReceiver(NetworkManager.Side.C2S, lockTargetId, ((buf, context)  -> {
            var posi = buf.readBlockPos();
            var lockTarget = buf.readBoolean();
            context.queue(() -> {
                var be = context.getPlayer().level().getBlockEntity(posi);
                if (be instanceof MailboxBlockEntity mailboxBlockEntity) {
                    mailboxBlockEntity.lockTarget = lockTarget;
                    mailboxBlockEntity.setChanged();
                }
            });
        }));
    }
}
