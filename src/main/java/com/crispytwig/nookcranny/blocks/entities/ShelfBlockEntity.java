package com.crispytwig.nookcranny.blocks.entities;

import com.crispytwig.nookcranny.blocks.ShelfBlock;
import com.crispytwig.nookcranny.registry.NCBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Clearable;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;

public class ShelfBlockEntity extends BlockEntity implements Clearable {
    private final NonNullList<ItemStack> items = NonNullList.withSize(4, ItemStack.EMPTY);

    public ShelfBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(NCBlockEntities.SHELF, blockPos, blockState);
    }

    public NonNullList<ItemStack> getItems() {
        return this.items;
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


    public boolean placeItem(ItemStack stack, int position) {
        ItemStack itemstack = this.items.get(position);
        SlabType half = this.getBlockState().getValue(ShelfBlock.HALF);

        if (!itemstack.isEmpty()) return false;

        if (half == SlabType.TOP && position == 0) this.items.set(0, stack.split(stack.getCount()));
        else if (half == SlabType.TOP && position == 2) this.items.set(2, stack.split(stack.getCount()));
        else if (half == SlabType.BOTTOM && position == 1) this.items.set(1, stack.split(stack.getCount()));
        else if (half == SlabType.BOTTOM && position == 3) this.items.set(3, stack.split(stack.getCount()));
        else if (half == SlabType.DOUBLE) this.items.set(position, stack.split(stack.getCount()));
        else return false;

        this.markUpdated();
        return true;
    }

    public boolean removeItem(int index, Player player, Level level) {
        if (this.items.get(index).isEmpty()) return false;

        if (level.isClientSide()) {
            player.playSound(SoundEvents.ITEM_PICKUP);
            return true;
        }

        ItemStack item = this.items.get(index).copy();
        player.setItemSlot(EquipmentSlot.MAINHAND, item);
        this.items.set(index, ItemStack.EMPTY);
        this.markUpdated();
        return true;
    }

    public boolean dropItem(int index, Level level) {
        ItemStack item = this.items.get(index).copy();
        Containers.dropItemStack(level, worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), item);
        this.items.set(index, ItemStack.EMPTY);
        this.markUpdated();
        return true;
    }

    private void markUpdated() {
        this.setChanged();
        this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
    }

    public void clearContent() {
        this.items.clear();
    }

    public void removeAllItems() {
        boolean update = false;
        for (int i = 0; i < items.size(); i++) {
            if (this.items.get(i).isEmpty()) continue;
            double posX = worldPosition.getX() + 0.3 + 0.4 * (i % 2);
            double posY = worldPosition.getY() + 1.0;
            double posZ = worldPosition.getZ() + 0.3 + 0.4 * (i / 2);

            assert this.level != null;
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