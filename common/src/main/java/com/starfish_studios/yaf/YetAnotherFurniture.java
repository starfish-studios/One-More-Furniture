package com.starfish_studios.yaf;

import com.starfish_studios.yaf.block.entity.MailboxBlockEntity;
import com.starfish_studios.yaf.events.*;
import com.starfish_studios.yaf.inventory.MailboxMenu;
import com.starfish_studios.yaf.registry.*;
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

public class YetAnotherFurniture {
    public static final String MOD_ID = "yaf";
    public static final Supplier<RegistrarManager> REGISTRIES = Suppliers.memoize(() -> RegistrarManager.get(MOD_ID));

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static ResourceLocation id(String id){
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, id);
    }

    //Moved from Client-side only code so it can be registered on server
    public static ResourceLocation lockTargetId = id("switch_lock");



    public static final RegistrySupplier<CreativeModeTab> MAIN = TABS.register("main",
            () -> CreativeTabRegistry.create(builder -> {
                builder.title(Component.translatable("itemGroup.yaf.tab"));
                builder.icon(() -> new ItemStack(YAFItems.BENCHES.get(YAFWoodType.OAK).get()));
                builder.displayItems((params, output) -> {
                    output.accept(YAFItems.COPPER_SAW.get().getDefaultInstance());

                    addAll(output, YAFItems.SOFAS);
                    addAll(output, YAFItems.CURTAINS);
                    addAll(output, YAFItems.BENCHES);
                    addAll(output, YAFItems.DRAWERS);
                    addAll(output, YAFItems.TALL_STOOLS);
                    addAll(output, YAFItems.FLOWER_BASKETS);
                    addAll(output, YAFItems.LAMPS);
                    addAll(output, YAFItems.MAIL_BOXES);
                    addAll(output, YAFItems.SHELVES);
                    addAll(output, YAFItems.TABLES);
                    addAll(output, YAFItems.CHAIRS);
                    addAll(output, YAFItems.FANS);
                    addAll(output, YAFItems.CABINET);

                    // Add standalone items
                    output.accept(YAFItems.SPIGOT.get().getDefaultInstance());
                    output.accept(YAFItems.AMETHYST_WIND_CHIMES.get().getDefaultInstance());
                    output.accept(YAFItems.BAMBOO_WIND_CHIMES.get().getDefaultInstance());
                    output.accept(YAFItems.BAMBOO_STRIPPED_WIND_CHIMES.get().getDefaultInstance());
                    output.accept(YAFItems.BONE_WIND_CHIMES.get().getDefaultInstance());
                    output.accept(YAFItems.COPPER_WIND_CHIMES.get().getDefaultInstance());
                    output.accept(YAFItems.ECHO_SHARD_WIND_CHIMES.get().getDefaultInstance());
                });
            })
    );

    private static void addAll(CreativeModeTab.Output output, Map<?, RegistrySupplier<BlockItem>> map) {
        map.values().forEach(supplier -> output.accept(supplier.get().getDefaultInstance()));
    }


    public static void init() {
        MidnightConfig.init(MOD_ID, YAFConfig.class);

        TABS.register();
        YAFSoundEvents.SOUNDS.register();
        YAFBlocks.BLOCKS.register();
        YAFItems.ITEMS.register();
        YAFBlockEntities.BLOCK_ENTITY_TYPES.register();
        YAFMenus.MENUS.register();
        YAFEntities.ENTITY_TYPES.register();

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
