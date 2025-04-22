package com.starfish_studios.yaf.block.entity;

import com.starfish_studios.yaf.block.properties.ChairType;
import com.starfish_studios.yaf.block.properties.ColorList;
import com.starfish_studios.yaf.block.properties.Cushionable;
import com.starfish_studios.yaf.registry.YAFBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;


public class TallStoolBlockEntity extends BlockEntity implements Cushionable {

    private ColorList color = ColorList.EMPTY;

    public TallStoolBlockEntity(BlockPos pos, BlockState blockState) {
        super(YAFBlockEntities.TALL_STOOL.get(), pos, blockState);
    }

    @Override
    public ColorList getColor() {
        return color;
    }

    @Override
    public void setColor(ColorList color) {
        this.color = color;
        setChanged();
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag compound = super.getUpdateTag();
        saveAdditional(compound);
        return compound;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        ColorList.CODEC.encodeStart(NbtOps.INSTANCE, color)
                .result()
                .ifPresent(encoded -> tag.put("Color", encoded));
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("Color", Tag.TAG_STRING)) {
            this.color = ColorList.CODEC.parse(NbtOps.INSTANCE, tag.get("Color"))
                    .result()
                    .orElse(ColorList.WHITE);
        }
    }
}