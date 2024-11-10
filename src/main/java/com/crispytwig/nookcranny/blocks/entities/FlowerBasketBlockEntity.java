package com.crispytwig.nookcranny.blocks.entities;

import com.crispytwig.nookcranny.registry.NCBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Clearable;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class FlowerBasketBlockEntity extends BlockEntity implements Clearable {
    private final NonNullList<ItemStack> items = NonNullList.withSize(2, ItemStack.EMPTY);

    public  FlowerBasketBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(NCBlockEntities.FLOWER_BASKET, blockPos, blockState);
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
    public CompoundTag getUpdateTag() {
        CompoundTag compoundtag = new CompoundTag();
        ContainerHelper.saveAllItems(compoundtag, this.items, true);
        return compoundtag;
    }

    public boolean placeFlower(ItemStack stack, int slot) {

        ItemStack itemstack = this.items.get(slot);
        if (itemstack.isEmpty()) {
            Block block = ((BlockItem)stack.getItem()).getBlock();
            this.getLevel().playSound(null, this.getBlockPos(), block.getSoundType(block.defaultBlockState()).getPlaceSound(), SoundSource.BLOCKS, 1.0F, 1.0F);
            this.items.set(slot, stack.split(1));
            this.markUpdated();
            return true;
        }

        return false;
    }

    private void markUpdated() {
        this.setChanged();
        this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
    }


    @Override
    public void clearContent() {
        this.items.clear();
    }

    public void removeAllItems() {
        boolean update = false;
        for (int i = 0; i < items.size(); i++) {
            if (this.items.get(i).isEmpty()) continue;
            double posX = worldPosition.getX() + 0.5;
            double posY = worldPosition.getY() + 0.5;
            double posZ = worldPosition.getZ() + 0.5;

            ItemEntity entity = new ItemEntity(this.level, posX, posY + 0.1, posZ, this.items.get(i).copy());
            this.level.addFreshEntity(entity);
            this.items.set(i, ItemStack.EMPTY);
            update = true;

        }
        if (update) {
            this.markUpdated();
        }
    }
}
