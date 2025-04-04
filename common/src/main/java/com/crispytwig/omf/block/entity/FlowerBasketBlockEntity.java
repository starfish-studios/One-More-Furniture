package com.crispytwig.omf.block.entity;

import com.crispytwig.omf.registry.OMFBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Clearable;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class FlowerBasketBlockEntity extends BlockEntity implements Clearable {
    private final NonNullList<ItemStack> items = NonNullList.withSize(2, ItemStack.EMPTY);

    public FlowerBasketBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(OMFBlockEntities.FLOWER_BASKET.get(), blockPos, blockState);
    }

    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    public Item getItemFromSlot(int slot) {
        return this.items.get(slot).getItem();
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.items.clear();
        ContainerHelper.loadAllItems(tag, this.items);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        ContainerHelper.saveAllItems(tag, this.items, true);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        CompoundTag compoundtag = new CompoundTag();
        ContainerHelper.saveAllItems(compoundtag, this.items, true);
        return compoundtag;
    }

    public boolean placeFlower(ItemStack stack, int slot) {
        ItemStack itemstack = this.items.get(slot);
        if (itemstack.isEmpty()) {
            Block block = ((BlockItem)stack.getItem()).getBlock();
            Objects.requireNonNull(this.getLevel()).playSound(null, this.getBlockPos(), block.getSoundType(block.defaultBlockState()).getPlaceSound(), SoundSource.BLOCKS, 1.0F, 1.0F);
            this.items.set(slot, stack.split(1));
            this.markUpdated();
            return true;
        }
        return false;
    }

    public void removeFlower(int slot) {
        if (!this.items.get(slot).isEmpty()) {
            this.items.set(slot, ItemStack.EMPTY);
            this.markUpdated();
        }
    }

    private void markUpdated() {
        this.setChanged();
        Objects.requireNonNull(this.getLevel()).sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
    }

    @Override
    public void clearContent() {
        this.items.clear();
    }

}
