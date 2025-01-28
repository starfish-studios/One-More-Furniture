//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.crispytwig.nookcranny.blocks.entities;

import com.crispytwig.nookcranny.blocks.MailboxBlock;
import com.crispytwig.nookcranny.blocks.properties.FlagStatus;
import com.crispytwig.nookcranny.inventory.MailboxMenu;
import com.crispytwig.nookcranny.registry.NCBlockEntities;
import com.crispytwig.nookcranny.world.NCSavedData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
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
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;

import java.util.*;
import java.util.function.Function;

public class MailboxBlockEntity extends RandomizableContainerBlockEntity {
    private NonNullList<ItemStack> items;
    private final ContainerOpenersCounter openersCounter;

    public MailboxBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(NCBlockEntities.MAILBOX, blockPos, blockState);
        this.items = NonNullList.withSize(27, ItemStack.EMPTY);
        this.openersCounter = new ContainerOpenersCounter() {
            protected void onOpen(Level level, BlockPos blockPos, BlockState blockState) {
                MailboxBlockEntity.this.playSound(blockState, SoundEvents.IRON_TRAPDOOR_OPEN);
                MailboxBlockEntity.this.updateBlockState(blockState, true);
            }

            protected void onClose(Level level, BlockPos blockPos, BlockState blockState) {
                MailboxBlockEntity.this.playSound(blockState, SoundEvents.IRON_TRAPDOOR_CLOSE);
                MailboxBlockEntity.this.updateBlockState(blockState, false);
            }

            protected void openerCountChanged(Level level, BlockPos blockPos, BlockState blockState, int i, int j) {
            }

            protected boolean isOwnContainer(Player player) {
                if (player.containerMenu instanceof MailboxMenu) {
                    Container container = ((MailboxMenu)player.containerMenu).getContainer();
                    return container == MailboxBlockEntity.this;
                } else {
                    return false;
                }
            }
        };
    }

    public Component getMailboxName() {
        return this.getDisplayName();
    }


    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        if (!this.trySaveLootTable(compoundTag)) {
            ContainerHelper.saveAllItems(compoundTag, this.items);
        }

    }

    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(compoundTag)) {
            ContainerHelper.loadAllItems(compoundTag, this.items);
        }

    }

    public void sendToMailbox() {
        if (level == null) {
            return;
        }

        for (ItemStack itemStack : this.items) {
            if (itemStack.hasCustomHoverName()) {
                String name = itemStack.getHoverName().getString();

                BlockPos blockPos = resolveBlockPos(name);
                if (blockPos == null) {
                    continue;
                }

                var server = this.level.getServer();
                if (server != null) {
                    for (Level dimLevel : server.getAllLevels()) {
                        MailboxBlockEntity mailboxBlockEntity = (MailboxBlockEntity) dimLevel.getBlockEntity(blockPos);

                        if (mailboxBlockEntity != null) {

                            boolean validFlagState = this.getBlockState().getValue(MailboxBlock.FLAG_STATUS) == FlagStatus.UP;
                            boolean validTargetFlag = mailboxBlockEntity.getBlockState().getValue(MailboxBlock.FLAG_STATUS) == FlagStatus.DOWN;

                            if (validFlagState && validTargetFlag) {
                                for (int j = 0; j < this.items.size(); j++) {
                                    ItemStack itemStack1 = this.items.get(j).copy();
                                    if (!itemStack1.isEmpty() && mailboxBlockEntity.items.get(j).isEmpty()) {
                                        mailboxBlockEntity.items.set(j, itemStack1);
                                        items.set(j, ItemStack.EMPTY);
                                    }

                                }
                                break;
                            }

                        } else {
                            System.out.println("Can't find Mailbox at: " + blockPos);
                        }
                    }
                }
            }
        }
    }

    private BlockPos resolveBlockPos(String name) {
        List<Function<String, BlockPos>> resolvers = List.of(
                this::checkCoordinates,
                this::checkPlayerName,
                this::checkMailboxName
        );

        for (Function<String, BlockPos> resolver : resolvers) {
            BlockPos blockPos = resolver.apply(name);
            if (blockPos != null) {
                return blockPos;
            }
        }

        return null;
    }

    /**
     * @param name name of the mailbox
     * @return a BlockPos if the level saved state has a mailbox name present
     */
    private BlockPos checkMailboxName(String name) {
        if (level instanceof ServerLevel serverLevel) {
            var data = NCSavedData.getMailboxes(serverLevel);

            return data.mailboxes.stream()
                    .filter(mailbox -> mailbox.name().equals(name))
                    .map(mailbox -> mailbox.globalPos().pos())
                    .findFirst()
                    .orElse(null);
        }

        return null;
    }

    /**
     * @param name name of the item's target mailbox
     * @return a BlockPos if the level saved state has a player name matching the name param
     */
    private BlockPos checkPlayerName(String name) {
        if (level instanceof ServerLevel serverLevel) {
            var data = NCSavedData.getMailboxes(serverLevel);

            return data.mailboxes.stream()
                    .filter(mailbox -> mailbox.playerName().equals(name))
                    .map(mailbox -> mailbox.globalPos().pos())
                    .findFirst()
                    .orElse(null);
        }

        return null;
    }

    /**
     * @param name name of the item's target mailbox in coordinates.
     * @return a BlockPos if the param name can be translated to a valid BlockPos
     */
    private BlockPos checkCoordinates(String name) {
        if (name.matches("-?\\d+ -?\\d+ -?\\d+")) {
            String[] coords = name.split(" ");

            if (coords.length == 3) {
                try {
                    int x = Integer.parseInt(coords[0]);
                    int y = Integer.parseInt(coords[1]);
                    int z = Integer.parseInt(coords[2]);

                    return new BlockPos(x, y, z).immutable();
                } catch (NumberFormatException e) {
                    System.out.println("Invalid coordinate format: " + name);
                }
            } else {
                System.out.println("Coordinate name format is invalid: " + name);
            }
        }
        return null;
    }

    public int getContainerSize() {
        return 27;
    }

    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    protected void setItems(NonNullList<ItemStack> nonNullList) {
        this.items = nonNullList;
    }

    protected Component getDefaultName() {
        return Component.translatable("container.mailbox");
    }

    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new MailboxMenu(i, inventory, this);
    }

    public void startOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.incrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }

    }

    public void stopOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.decrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }

    }

    public void recheckOpen() {
        if (!this.remove) {
            this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
        }

    }

    void updateBlockState(BlockState blockState, boolean bl) {
        this.level.setBlock(this.getBlockPos(), blockState.setValue(MailboxBlock.OPEN, bl), 3);
    }

    void playSound(BlockState blockState, SoundEvent soundEvent) {
        Vec3i vec3i = blockState.getValue(MailboxBlock.FACING).getNormal();
        double d = (double)this.worldPosition.getX() + 0.5 + (double)vec3i.getX() / 2.0;
        double e = (double)this.worldPosition.getY() + 0.5 + (double)vec3i.getY() / 2.0;
        double f = (double)this.worldPosition.getZ() + 0.5 + (double)vec3i.getZ() / 2.0;
        this.level.playSound(null, d, e, f, soundEvent, SoundSource.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
    }

    public static void sendItemsTick(Level level, BlockPos pos, BlockState state, MailboxBlockEntity mailboxBlockEntity) {
        if (!level.isClientSide) {
            mailboxBlockEntity.sendToMailbox();
        }
    }
}
