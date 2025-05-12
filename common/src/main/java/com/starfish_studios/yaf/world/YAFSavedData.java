package com.starfish_studios.yaf.world;

import com.starfish_studios.yaf.YetAnotherFurniture;
import com.mojang.serialization.DataResult;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.RandomSequences;
import net.minecraft.world.level.ForcedChunksSavedData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class YAFSavedData extends SavedData {

    public final List<MailboxData> mailboxes = new ArrayList<>();

    public static YAFSavedData load(CompoundTag nbt, HolderLookup.Provider registries) {
        var data = new YAFSavedData();
        ListTag mailboxesTag = nbt.getList("Mailboxes", Tag.TAG_COMPOUND);
        for (int i = 0; i < mailboxesTag.size(); i++) {
            var mailNbt = mailboxesTag.getCompound(i);

            var name = mailNbt.getString("Name");
            var playerName = mailNbt.getString("PlayerName");
            var pos = NbtUtils.readBlockPos(mailNbt, "GlobalPos");
            var dim = Level.RESOURCE_KEY_CODEC.parse(NbtOps.INSTANCE, mailNbt.get("MailboxDimension")).result();
            if (dim.isPresent() && pos.isPresent()) {
                var newPair = new MailboxData(playerName, name, GlobalPos.of(dim.get(), pos.get()));
                data.mailboxes.add(newPair);
            }
        }

        return data;
    }

    @Override
    public @NotNull CompoundTag save(@NotNull CompoundTag nbt,  HolderLookup.Provider registries) {

        var nbtList = new ListTag();

        for (MailboxData mailbox : mailboxes) {
            var mailNbt = new CompoundTag();
            mailNbt.putString("Name", mailbox.name());
            mailNbt.putString("PlayerName", mailbox.playerName());
            mailNbt.put("GlobalPos", NbtUtils.writeBlockPos(mailbox.globalPos.pos()));

            DataResult<Tag> result = Level.RESOURCE_KEY_CODEC.encodeStart(NbtOps.INSTANCE, mailbox.globalPos.dimension());

            result.result().ifPresent(tag -> {
                mailNbt.put("MailboxDimension", tag);
            });

            nbtList.add(mailNbt);
        }
        nbt.put("Mailboxes", nbtList);

        return nbt;
    }

    private static SavedData.Factory<YAFSavedData> factory() {
        return new SavedData.Factory<>(YAFSavedData::new, YAFSavedData::load, DataFixTypes.LEVEL);
    }

    public static YAFSavedData getMailboxes(ServerLevel level) {
        return level.getServer().overworld().getDataStorage().computeIfAbsent(factory(), YetAnotherFurniture.MOD_ID);
    }

    public record MailboxData(String playerName, String name, GlobalPos globalPos) {}
}