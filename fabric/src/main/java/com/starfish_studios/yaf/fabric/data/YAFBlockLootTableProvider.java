package com.starfish_studios.yaf.fabric.data;

import com.starfish_studios.yaf.block.ChairBlock;
import com.starfish_studios.yaf.block.ShelfBlock;
import com.starfish_studios.yaf.block.TallStoolBlock;
import com.starfish_studios.yaf.block.properties.ColorList;
import com.starfish_studios.yaf.registry.YAFBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.Map;

public class YAFBlockLootTableProvider extends FabricBlockLootTableProvider {
    public YAFBlockLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {

        YAFBlocks.BLOCKS.forEach((blockRegistrySupplier -> {
            var block = blockRegistrySupplier.get();
            if (block instanceof ChairBlock) {
                this.add(block, createLootTableForChair(block));
            } else if (block instanceof ShelfBlock) {
                this.add(block, createShelfTable(block));
            } else {
                dropSelf(block);
            }
        }));
    }

    public Map<ColorList, Item> cushionToCarpetMap = Map.ofEntries(
            Map.entry(ColorList.RED, Items.RED_CARPET),
            Map.entry(ColorList.ORANGE, Items.ORANGE_CARPET),
            Map.entry(ColorList.YELLOW, Items.YELLOW_CARPET),
            Map.entry(ColorList.LIME, Items.LIME_CARPET),
            Map.entry(ColorList.GREEN, Items.GREEN_CARPET),
            Map.entry(ColorList.CYAN, Items.CYAN_CARPET),
            Map.entry(ColorList.BLUE, Items.BLUE_CARPET),
            Map.entry(ColorList.LIGHT_BLUE, Items.LIGHT_BLUE_CARPET),
            Map.entry(ColorList.PINK, Items.PINK_CARPET),
            Map.entry(ColorList.MAGENTA, Items.MAGENTA_CARPET),
            Map.entry(ColorList.PURPLE, Items.PURPLE_CARPET),
            Map.entry(ColorList.WHITE, Items.WHITE_CARPET),
            Map.entry(ColorList.LIGHT_GRAY, Items.LIGHT_GRAY_CARPET),
            Map.entry(ColorList.GRAY, Items.GRAY_CARPET),
            Map.entry(ColorList.BROWN, Items.BROWN_CARPET),
            Map.entry(ColorList.BLACK, Items.BLACK_CARPET)
    );

    public LootTable.Builder createLootTableForChair(Block block) {
        LootTable.Builder builder = LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(block))
                );

        for (ColorList color : ColorList.values()) {
            Item carpetItem = cushionToCarpetMap.get(color);
            if (carpetItem != null) {
                builder.withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(carpetItem)
                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                                .hasProperty(TallStoolBlock.CUSHION, color.name().toLowerCase())))));
            }
        }

        return builder;
    }

    public LootTable.Builder createShelfTable(Block block) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(block, LootItem.lootTableItem(block)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                                        .hasProperty(ShelfBlock.HALF, SlabType.DOUBLE)
                                                )
                                        )
                                )
                        ))
                );
    }
}
