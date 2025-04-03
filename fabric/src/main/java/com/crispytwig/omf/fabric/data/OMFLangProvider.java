package com.crispytwig.omf.fabric.data;

import com.crispytwig.omf.registry.OMFBlocks;
import com.crispytwig.omf.registry.OMFItems;
import dev.architectury.registry.registries.RegistrySupplier;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.world.item.Item;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class OMFLangProvider extends FabricLanguageProvider {
    public OMFLangProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder builder) {

        OMFItems.ITEMS.forEach(supplier -> {
            String name = supplier.getId().getPath();
            String prepend = "item.omf." + (name);
            builder.add(prepend, formatString(name));
        });

        OMFBlocks.BLOCKS.forEach(supplier -> {
            String name = supplier.getId().getPath();
            String prepend = "block.omf." + (name);
            builder.add(prepend, formatString(name));
        });

        builder.add("itemGroup.omf.tab", "One More Furniture");
        builder.add( "block_type.omf.table", "%s Table");
        builder.add( "block_type.omf.chair", "%s Chair");
        builder.add( "block_type.omf.nightstand", "%s Nightstand");
        builder.add( "block_type.omf.mailbox", "%s Mailbox");
        builder.add( "block_type.omf.lamp", "%s Lamp");
        builder.add( "block_type.omf.shelf", "%s Shelf");


        builder.add( "container.drawer", "Drawer");
        builder.add( "container.drawer_top", "Top Drawer");
        builder.add( "container.drawer_bottom", "Bottom Drawer");
        builder.add( "container.mailbox", "Mailbox");
        builder.add( "container.mailbox.player_name", "%s's Mailbox");
        builder.add( "container.mailbox.send", "Send to Mailbox:");
        builder.add( "container.mailbox.error", "Cannot send!");

        builder.add("container.cabinet_left", "Left Cabinet");
        builder.add("container.cabinet_right", "Right Cabinet");

        builder.add( "omf.mailbox.sending", "Sending item(s) from %s at %s");
        builder.add( "omf.mailbox.receiving", "Receiving item(s) from %s at %s");
        builder.add( "omf.mailbox.at", "at");
    }

    private static final Set<String> LOWERCASE_WORDS = new HashSet<>(Arrays.asList("and", "or"));

    public static String formatString(String input) {
        String[] words = input.split("_");

        return Arrays.stream(words).map(OMFLangProvider::capitalizeWord).collect(Collectors.joining(" "));
    }

    private static String capitalizeWord(String word) {
        String lowerWord = word.toLowerCase();
        if (LOWERCASE_WORDS.contains(lowerWord)) {
            return lowerWord;
        }
        return Character.toUpperCase(lowerWord.charAt(0)) + lowerWord.substring(1);
    }
}