package com.crispytwig.nookcranny;

import com.crispytwig.nookcranny.blocks.*;
import com.crispytwig.nookcranny.blocks.entities.ShelfBlockEntity;
import com.crispytwig.nookcranny.registry.NCBlockEntities;
import com.crispytwig.nookcranny.registry.NCBlocks;
import net.mehvahdjukaar.every_compat.api.*;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;

import static com.crispytwig.nookcranny.NookAndCranny.MOD_ID;

public class NCModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> tables;
    public final SimpleEntrySet<WoodType, Block> chairs;

    public final SimpleEntrySet<WoodType, Block> shelves;
    public final SimpleEntrySet<WoodType, Block> lamps;
    public final SimpleEntrySet<WoodType, Block> mailboxes;
    public final SimpleEntrySet<WoodType, Block> nightstands;

    public static void init(String modId) {
        new NCModule(MOD_ID);
    }

    public NCModule(String modId) {
        super(modId, "nac");

        SimpleModule nookandcranny = new SimpleModule("nookcranny", "nc");

        tables = SimpleEntrySet.builder(WoodType.class, "table",
                        getModBlock("oak_table"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TableBlock(Utils.copyPropertySafe(NCBlocks.OAK_TABLE)))
                .addTag(modRes("tables"), Registries.BLOCK)
                .addTag(modRes("tables"), Registries.ITEM)
                .copyParentDrop()
                .defaultRecipe()
                .setTab(getModTab(MOD_ID))
                .setTabMode(TabAddMode.AFTER_SAME_TYPE)
                .setRenderType(() -> RenderType::cutout)
                .useMergedPalette()
                .addTexture(modRes("block/table/oak_table"))
                .build();

        this.addEntry(tables);

        chairs = SimpleEntrySet.builder(WoodType.class, "chair",
                        getModBlock("oak_chair"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ChairBlock(Utils.copyPropertySafe(NCBlocks.OAK_CHAIR)))
                .addTag(modRes("chairs"), Registries.BLOCK)
                .addTag(modRes("chairs"), Registries.ITEM)
                .copyParentDrop()
                .defaultRecipe()
                .setTab(getModTab(MOD_ID))
                .setTabMode(TabAddMode.AFTER_SAME_TYPE)
                .setRenderType(() -> RenderType::cutout)
                .useMergedPalette()
                .addTexture(modRes("block/chair/oak_chair"))
                .build();

        this.addEntry(chairs);

        lamps = SimpleEntrySet.builder(WoodType.class, "lamp",
                        getModBlock("oak_lamp"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new LampBlock(Utils.copyPropertySafe(NCBlocks.OAK_LAMP)
                                .lightLevel(state -> state.getValue(LampBlock.LIT) ? 15 : 0)))
                .addTag(modRes("lamps"), Registries.BLOCK)
                .addTag(modRes("lamps"), Registries.ITEM)
                .copyParentDrop()
                .defaultRecipe()
                .setTab(getModTab(MOD_ID))
                .setTabMode(TabAddMode.AFTER_SAME_TYPE)
                .setRenderType(() -> RenderType::translucent)
                .useMergedPalette()
//                .addTexture(modRes("block/lamp/oak_lamp"))
                .addTextureM(modRes("block/lamp/oak_lamp"), modRes("block/lamp/lamp_mask"))
                .build();

        this.addEntry(lamps);

        mailboxes = SimpleEntrySet.builder(WoodType.class, "mailbox",
                        getModBlock("oak_mailbox"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new MailboxBlock(Utils.copyPropertySafe(NCBlocks.OAK_MAILBOX)))
                .addTag(modRes("mailboxes"), Registries.BLOCK)
                .addTag(modRes("mailboxes"), Registries.ITEM)
                .copyParentDrop()
                .defaultRecipe()
                .setTab(getModTab(MOD_ID))
                .setTabMode(TabAddMode.AFTER_SAME_TYPE)
                .setRenderType(() -> RenderType::cutout)
                .useMergedPalette()
                .addTexture(modRes("block/mailbox/post/oak_post"))
                .build();

        this.addEntry(mailboxes);

        shelves = SimpleEntrySet.builder(WoodType.class, "shelf",
                        getModBlock("oak_shelf"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ShelfBlock(Utils.copyPropertySafe(NCBlocks.OAK_SHELF)))
                .addTag(modRes("shelves"), Registries.BLOCK)
                .addTag(modRes("shelves"), Registries.ITEM)
                .addTile(getModTile("shelf"))
                .copyParentDrop()
                .defaultRecipe()
                .setTab(getModTab(MOD_ID))
                .setTabMode(TabAddMode.AFTER_SAME_TYPE)
                .setRenderType(() -> RenderType::cutout)
                .useMergedPalette()
                .addTexture(modRes("block/shelf/oak_shelf"))
                .build();

        this.addEntry(shelves);

        nightstands = SimpleEntrySet.builder(WoodType.class, "nightstand",
                        getModBlock("oak_nightstand"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new NightstandBlock(Utils.copyPropertySafe(NCBlocks.OAK_NIGHTSTAND)))
                .addTag(modRes("nightstands"), Registries.BLOCK)
                .addTag(modRes("nightstands"), Registries.ITEM)
                .copyParentDrop()
                .defaultRecipe()
                .setTab(getModTab(MOD_ID))
                .setTabMode(TabAddMode.AFTER_SAME_TYPE)
                .setRenderType(() -> RenderType::cutout)
                .useMergedPalette()
                .addTexture(modRes("block/nightstand/oak_nightstand_core"))
                .addTexture(modRes("block/nightstand/oak_nightstand_bits"))
                .addTexture(modRes("block/nightstand/oak_nightstand"))
                .build();

        this.addEntry(nightstands);


        EveryCompatAPI.registerModule(nookandcranny);
    }
}
