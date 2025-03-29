package com.crispytwig.nookcranny.data;

import com.crispytwig.nookcranny.registry.NCBlocks;
import com.crispytwig.nookcranny.registry.NCItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class NCLangProvider extends FabricLanguageProvider {
    public NCLangProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder builder) {

        for (String item : NCItems.ITEM_NAMES) {
            String prepend = "item.nookcranny." + (item);
            builder.add(prepend, formatString(item));
        }

        for (String item : NCBlocks.BLOCK_NAMES) {
            String prepend = "block.nookcranny." + (item);
            builder.add(prepend, formatString(item));
        }

        builder.add("itemGroup.nookcranny.tab", "Nook and Cranny");
        builder.add( "block_type.nookcranny.table", "%s Table");
        builder.add( "block_type.nookcranny.chair", "%s Chair");
        builder.add( "block_type.nookcranny.nightstand", "%s Nightstand");
        builder.add( "block_type.nookcranny.mailbox", "%s Mailbox");
        builder.add( "block_type.nookcranny.lamp", "%s Lamp");
        builder.add( "block_type.nookcranny.shelf", "%s Shelf");


        builder.add( "container.drawer", "Drawer");
        builder.add( "container.drawer_top", "Top Drawer");
        builder.add( "container.drawer_bottom", "Bottom Drawer");
        builder.add( "container.mailbox", "Mailbox");
        builder.add( "container.mailbox.player_name", "%s's Mailbox");
        builder.add( "container.mailbox.send", "Send to Mailbox:");
        builder.add( "container.mailbox.error", "Cannot send!");

        builder.add("container.cabinet_left", "Left Cabinet");
        builder.add("container.cabinet_right", "Right Cabinet");

        builder.add( "nookcranny.mailbox.sending", "Sending item(s) from %s at %s");
        builder.add( "nookcranny.mailbox.receiving", "Receiving item(s) from %s at %s");
        builder.add( "nookcranny.mailbox.at", "at");
    }

    private static final Set<String> LOWERCASE_WORDS = new HashSet<>(Arrays.asList("and", "or"));

    public static String formatString(String input) {
        String[] words = input.split("_");

        return Arrays.stream(words).map(NCLangProvider::capitalizeWord).collect(Collectors.joining(" "));
    }

    private static String capitalizeWord(String word) {
        String lowerWord = word.toLowerCase();
        if (LOWERCASE_WORDS.contains(lowerWord)) {
            return lowerWord;
        }
        return Character.toUpperCase(lowerWord.charAt(0)) + lowerWord.substring(1);
    }
}