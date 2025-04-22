package com.starfish_studios.yaf.fabric.data;

import com.starfish_studios.yaf.block.ShelfBlock;
import com.starfish_studios.yaf.registry.YAFBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public class YAFBlockLootTableProvider extends FabricBlockLootTableProvider {
    public YAFBlockLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {

        YAFBlocks.BLOCKS.forEach((blockRegistrySupplier -> {
            var block = blockRegistrySupplier.get();
            if (block instanceof ShelfBlock) {
                this.add(block, createShelfTable(block));
            } else {
                dropSelf(block);
            }
        }));
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
