package com.starfish_studios.yaf.block.entity;

import com.starfish_studios.yaf.registry.YAFBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Clearable;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class ShelfBlockEntity extends BlockEntity implements Clearable {
    private final NonNullList<ItemStack> items = NonNullList.withSize(4, ItemStack.EMPTY);

    public ShelfBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(YAFBlockEntities.SHELF.get(), blockPos, blockState);
    }

    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.items.clear();
        ContainerHelper.loadAllItems(tag, this.items, registries);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        ContainerHelper.saveAllItems(tag, this.items, true, registries);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag compoundtag = super.getUpdateTag(registries);
        ContainerHelper.saveAllItems(compoundtag, this.items, true, registries);
        return compoundtag;
    }


    public boolean removeItem(int index, Player player, Level level, boolean removeAll) {
        ItemStack shelfStack = this.items.get(index);
        if (shelfStack.isEmpty()) return false;

        int removeCount = removeAll ? shelfStack.getCount() : 1;
        ItemStack removed = shelfStack.copy();
        removed.setCount(removeCount);

        shelfStack.shrink(removeCount);
        if (shelfStack.getCount() <= 0) {
            this.items.set(index, ItemStack.EMPTY);
        }

        if (!player.addItem(removed.copy())) {
            Containers.dropItemStack(level, worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), removed);
        }

        this.markUpdated();
        return true;
    }

    public void dropItem(int index, Level level) {
        ItemStack item = this.items.get(index).copy();
        Containers.dropItemStack(level, worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), item);
        this.items.set(index, ItemStack.EMPTY);
        this.markUpdated();
    }

    public void markUpdated() {
        this.setChanged();
        if (this.getLevel() != null) {
            this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
        }
    }

    public void clearContent() {
        this.items.clear();
    }

}