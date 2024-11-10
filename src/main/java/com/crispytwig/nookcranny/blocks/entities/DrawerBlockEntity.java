//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.crispytwig.nookcranny.blocks.entities;

import com.crispytwig.nookcranny.blocks.DrawerBlock;
import com.crispytwig.nookcranny.inventory.DrawerMenu;
import com.crispytwig.nookcranny.registry.NCBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class DrawerBlockEntity extends RandomizableContainerBlockEntity {
    private static final SoundEvent SOUND_OPEN = SoundEvents.BARREL_OPEN;
    private static final SoundEvent SOUND_CLOSE = SoundEvents.BARREL_CLOSE;

    private NonNullList<ItemStack> items;
    private final ContainerOpenersCounter openersCounterTop;
    private final ContainerOpenersCounter openersCounterBottom;

    public DrawerBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(NCBlockEntities.DRAWER, blockPos, blockState);
        this.items = NonNullList.withSize(27, ItemStack.EMPTY);
        this.openersCounterTop = new ContainerOpenersCounter() {
            protected void onOpen(@NotNull Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockState) {
                DrawerBlockEntity.this.playSound(blockState, SOUND_OPEN);
                DrawerBlockEntity.this.updateTopState(blockState, true);
            }

            protected void onClose(@NotNull Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockState) {
                DrawerBlockEntity.this.playSound(blockState, SOUND_CLOSE);
                DrawerBlockEntity.this.updateTopState(blockState, false);
            }

            protected void openerCountChanged(@NotNull Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockState, int i, int j) {
            }

            protected boolean isOwnContainer(@NotNull Player player) {
                if (player.containerMenu instanceof DrawerMenu) {
                    Container container = ((DrawerMenu)player.containerMenu).getContainer();
                    return container == DrawerBlockEntity.this;
                } else {
                    return false;
                }
            }
        };
        this.openersCounterBottom = new ContainerOpenersCounter() {
            protected void onOpen(@NotNull Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockState) {
                DrawerBlockEntity.this.playSound(blockState, SOUND_OPEN);
                DrawerBlockEntity.this.updateBottomState(blockState, true);
            }

            protected void onClose(@NotNull Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockState) {
                DrawerBlockEntity.this.playSound(blockState, SOUND_CLOSE);
                DrawerBlockEntity.this.updateBottomState(blockState, false);
            }

            protected void openerCountChanged(@NotNull Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockState, int i, int j) {
            }

            protected boolean isOwnContainer(@NotNull Player player) {
                if (player.containerMenu instanceof DrawerMenu) {
                    Container container = ((DrawerMenu)player.containerMenu).getContainer();
                    return container == DrawerBlockEntity.this;
                } else {
                    return false;
                }
            }
        };
    }

    public Component getDrawerName() {
        return this.getDisplayName();
    }


    protected void saveAdditional(@NotNull CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        if (!this.trySaveLootTable(compoundTag)) {
            ContainerHelper.saveAllItems(compoundTag, this.items);
        }
        // Saves items when the inventory is closed or updated
        System.out.println("Drawer items saved!");

    }

    public void load(@NotNull CompoundTag compoundTag) {
        super.load(compoundTag);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(compoundTag)) {
            ContainerHelper.loadAllItems(compoundTag, this.items);
        }
        // Loads the items when the block is placed with saved NBT
        System.out.println("Drawer items loaded!");

    }

    public int getContainerSize() {
        return 27;
    }

    protected @NotNull NonNullList<ItemStack> getItems() {
        return this.items;
    }

    protected void setItems(@NotNull NonNullList<ItemStack> nonNullList) {
        this.items = nonNullList;
    }

    protected @NotNull Component getDefaultName() {
        return Component.translatable("container.drawer");
    }

    protected @NotNull AbstractContainerMenu createMenu(int i, @NotNull Inventory inventory) {
        return new DrawerMenu(i, inventory, this);
    }

    public void startOpen(@NotNull Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounterTop.incrementOpeners(player, Objects.requireNonNull(this.getLevel()), this.getBlockPos(), this.getBlockState());
        }

    }

    public void stopOpen(@NotNull Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounterTop.decrementOpeners(player, Objects.requireNonNull(this.getLevel()), this.getBlockPos(), this.getBlockState());
        }

    }

    public void recheckOpen() {
        if (!this.remove) {
            this.openersCounterTop.recheckOpeners(Objects.requireNonNull(this.getLevel()), this.getBlockPos(), this.getBlockState());
        }

    }

    void updateTopState(BlockState blockState, boolean bl) {
        assert this.level != null;
        this.level.setBlock(this.getBlockPos(), blockState.setValue(DrawerBlock.TOP_OPEN, bl), 3);
    }

    void updateBottomState(BlockState blockState, boolean bl) {
        assert this.level != null;
        this.level.setBlock(this.getBlockPos(), blockState.setValue(DrawerBlock.BOTTOM_OPEN, bl), 3);
    }

    void playSound(BlockState blockState, SoundEvent soundEvent) {
        Vec3i vec3i = blockState.getValue(DrawerBlock.FACING).getNormal();
        double d = (double)this.worldPosition.getX() + 0.5 + (double)vec3i.getX() / 2.0;
        double e = (double)this.worldPosition.getY() + 0.5 + (double)vec3i.getY() / 2.0;
        double f = (double)this.worldPosition.getZ() + 0.5 + (double)vec3i.getZ() / 2.0;
        assert this.level != null;
        this.level.playSound(null, d, e, f, soundEvent, SoundSource.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
    }
}
