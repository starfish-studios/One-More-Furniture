//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.crispytwig.nookcranny.blocks.entities;

import com.crispytwig.nookcranny.NookAndCranny;
import com.crispytwig.nookcranny.blocks.MailboxBlock;
import com.crispytwig.nookcranny.blocks.properties.FlagStatus;
import com.crispytwig.nookcranny.inventory.MailboxMenu;
import com.crispytwig.nookcranny.registry.NCBlockEntities;
import com.crispytwig.nookcranny.world.NCSavedData;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Function;

public class MailboxBlockEntity extends BlockEntity
        implements Container,
        Nameable {
    public boolean lockTarget = false;
    private NonNullList<ItemStack> items;
    private final ContainerOpenersCounter openersCounter;
    public String targetString = "";
    private int sendDelay = 20 * 2;
    @Nullable
    private Component name;
    public static ResourceLocation packetChannel = new ResourceLocation(NookAndCranny.MOD_ID, "sync_mailbox_fail");
    public static ResourceLocation packetChannel2 = new ResourceLocation(NookAndCranny.MOD_ID, "sync_mailbox_text_reset");

    public boolean failedToSend = false;

    public MailboxBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(NCBlockEntities.MAILBOX, blockPos, blockState);
        this.items = NonNullList.withSize(5, ItemStack.EMPTY);

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
        ContainerHelper.saveAllItems(compoundTag, this.items);
        if (!targetString.isEmpty()) {
            compoundTag.putString("Target", targetString);
        }
        compoundTag.putInt("SendDelay", sendDelay);
        if (this.name != null) {
            compoundTag.putString("CustomName", Component.Serializer.toJson(this.name));
        }
        compoundTag.putBoolean("FailedToSend", failedToSend);
        compoundTag.putBoolean("LockTarget", lockTarget);
    }

    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(compoundTag, this.items);
        if (compoundTag.contains("Target")) {
            targetString = compoundTag.getString("Target");
        }
        sendDelay = compoundTag.getInt("SendDelay");
        if (compoundTag.contains("CustomName", 8)) {
            this.name = Component.Serializer.fromJson(compoundTag.getString("CustomName"));
        }
        failedToSend = compoundTag.getBoolean("FailedToSend");
        lockTarget = compoundTag.getBoolean("LockTarget");
    }

    private static boolean canMergeItems(ItemStack stack1, ItemStack stack2) {
        return stack1.getCount() <= stack1.getMaxStackSize() && ItemStack.isSameItemSameTags(stack1, stack2);
    }

    private void serverTick() {
        boolean validFlagState = this.getBlockState().getValue(MailboxBlock.FLAG_STATUS) == FlagStatus.UP;
        if (validFlagState && !items.isEmpty() && !targetString.isEmpty()) {

            var targetMailbox = getTargetMailbox();

            if (targetMailbox != null) {

                if (sendDelay > 0) {
                    sendDelay--;
                } else {
                    sendDelay = 20 * 2;

                    if (sendToMailboxItems(targetMailbox)) {
                        failedToSend = false;
                        sendMessageState(failedToSend);
                        this.level.setBlock(this.getBlockPos(), this.getBlockState().setValue(MailboxBlock.FLAG_STATUS, FlagStatus.DOWN), 3);
                        if (!lockTarget) {
                            targetString = "";
                            for (ServerPlayer serverPlayer : PlayerLookup.tracking(this)) {
                                FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
                                buf.writeBlockPos(getBlockPos());
                                ServerPlayNetworking.send(serverPlayer, packetChannel2, buf);
                            }
                            setChanged();
                        }
                    } else {
                        sendMessageState(failedToSend);
                    }

                    setChanged();
                }
            } else {
                sendMessageState(failedToSend);
            }
        }
    }

    private void sendMessageState(boolean fail){
        for (ServerPlayer serverPlayer : PlayerLookup.tracking(this)) {
            FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
            buf.writeBlockPos(getBlockPos());
            buf.writeBoolean(fail);
            ServerPlayNetworking.send(serverPlayer, packetChannel, buf);
        }
    }

    private MailboxBlockEntity getTargetMailbox() {
        if (level == null || level.isClientSide) {
            return null;
        }

        GlobalPos globalPos = resolveBlockPos(targetString);
        if (globalPos == null) {
            return null;
        }

        var server = level.getServer();
        if (server == null) {
            return null;
        }

        var dimLevel = server.getLevel(globalPos.dimension());
        if (dimLevel == null || dimLevel.isClientSide) {
            return null;
        }

        var be = dimLevel.getBlockEntity(globalPos.pos());
        if (be instanceof MailboxBlockEntity) {
            return (MailboxBlockEntity) be;
        }

        return null;
    }

    private boolean sendToMailboxItems(MailboxBlockEntity targetMailbox) {
        boolean validFlagState = this.getBlockState().getValue(MailboxBlock.FLAG_STATUS) == FlagStatus.UP;
        boolean validTargetFlag = targetMailbox.getBlockState().getValue(MailboxBlock.FLAG_STATUS) == FlagStatus.DOWN;

        if (!validFlagState || !validTargetFlag) {
            return false;
        }

        boolean sentMail = false;

        for (int o = 0; o < this.items.size(); o++) {
            ItemStack toSendStack = this.items.get(o).copy();

            if (!toSendStack.isEmpty()) {
                boolean merged = false;

                // Try to merge into an existing stack
                for (int i = 0; i < targetMailbox.items.size(); i++) {
                    ItemStack targetStack = targetMailbox.items.get(i);

                    if (!targetStack.isEmpty() && canMergeItems(targetStack, toSendStack)) {
                        int spaceAvailable = targetStack.getMaxStackSize() - targetStack.getCount();
                        int transferAmount = Math.min(toSendStack.getCount(), spaceAvailable);

                        if (transferAmount > 0) {
                            targetStack.grow(transferAmount);
                            toSendStack.shrink(transferAmount);
                            sentMail = true;

                            if (toSendStack.isEmpty()) {
                                items.set(o, ItemStack.EMPTY);
                                merged = true;
                                break;
                            }
                        }
                    }
                }

                // If we couldn't merge, find an empty slot to insert into
                if (!merged) {
                    for (int i = 0; i < targetMailbox.items.size(); i++) {
                        if (targetMailbox.items.get(i).isEmpty()) {
                            targetMailbox.items.set(i, toSendStack);
                            items.set(o, ItemStack.EMPTY);
                            sentMail = true;
                            break;
                        }
                    }
                }
            }
        }

        return sentMail;
    }

    private GlobalPos resolveBlockPos(String name) {
        List<Function<String, GlobalPos>> resolvers = List.of(
                this::checkCoordinates,
                this::checkPlayerName,
                this::checkMailboxName
        );

        for (Function<String, GlobalPos> resolver : resolvers) {
            GlobalPos blockPos = resolver.apply(name);
            if (blockPos != null) {
                return blockPos;
            }
        }

        return null;
    }

    /**
     * @param name name of the mailbox
     * @return a GlobalPos if the level saved state has a mailbox name present
     */
    private GlobalPos checkMailboxName(String name) {
        if (level instanceof ServerLevel serverLevel) {
            var data = NCSavedData.getMailboxes(serverLevel);

            return data.mailboxes.stream()
                    .filter(mailbox -> mailbox.name().equals(name))
                    .map(NCSavedData.MailboxData::globalPos)
                    .findFirst()
                    .orElse(null);
        }

        return null;
    }

    /**
     * @param name name of the item's target mailbox
     * @return a GlobalPos if the level saved state has a player name matching the name param
     */
    private GlobalPos checkPlayerName(String name) {
        if (level instanceof ServerLevel serverLevel) {
            var data = NCSavedData.getMailboxes(serverLevel);

            return data.mailboxes.stream()
                    .filter(mailbox -> mailbox.playerName().equals(name))
                    .map(NCSavedData.MailboxData::globalPos)
                    .findFirst()
                    .orElse(null);
        }

        return null;
    }

    /**
     * @param name name of the item's target mailbox in coordinates.
     * @return a GlobalPos if the param name can be translated to a valid BlockPos
     */
    private GlobalPos checkCoordinates(String name) {
        if (name.matches("-?\\d+ -?\\d+ -?\\d+")) {
            String[] coords = name.split(" ");

            if (coords.length == 3) {
                try {
                    int x = Integer.parseInt(coords[0]);
                    int y = Integer.parseInt(coords[1]);
                    int z = Integer.parseInt(coords[2]);

                    return GlobalPos.of(this.level.dimension(), new BlockPos(x, y, z));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid coordinate format: " + name);
                }
            } else {
                System.out.println("Coordinate name format is invalid: " + name);
            }
        }
        return null;
    }

    protected Component getDefaultName() {
        return Component.translatable("container.mailbox");
    }

    @Override
    public int getContainerSize() {
        return 5;
    }

    @Override
    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    @Override
    public ItemStack getItem(int slot) {
        return items.get(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        ItemStack itemStack = ContainerHelper.removeItem(this.items, slot, amount);
        if (!itemStack.isEmpty()) {
            this.setChanged();
        }
        return itemStack;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(this.items, slot);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        this.items.set(slot, stack);
        if (stack.getCount() > this.getMaxStackSize()) {
            stack.setCount(this.getMaxStackSize());
        }
        this.setChanged();
    }

    @Override
    public boolean stillValid(Player player) {
        return Container.stillValidBlockEntity(this, player);
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
            mailboxBlockEntity.serverTick();
        }
    }

    @Override
    public void clearContent() {
        items.clear();
    }

    @Override
    public Component getName() {
        return name;
    }

    public void setCustomName(Component translatable) {
        this.name = translatable;
    }
}
