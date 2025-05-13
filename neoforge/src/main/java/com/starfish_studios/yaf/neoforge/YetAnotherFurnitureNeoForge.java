package com.starfish_studios.yaf.neoforge;

import com.starfish_studios.yaf.YetAnotherFurniture;
import com.starfish_studios.yaf.YetAnotherFurnitureClient;
import com.starfish_studios.yaf.events.ShelfInteractions;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.Map;

@Mod(YetAnotherFurniture.MOD_ID)
public class YetAnotherFurnitureNeoForge {

    /**
     * Set to true to print out the number of block states registered for each block type. For optimization purposes.
     */
    private static final boolean runBlockStateTest = false;

    public YetAnotherFurnitureNeoForge(IEventBus modEventBus, ModContainer modContainer) {
        YetAnotherFurniture.init();

        if (FMLEnvironment.dist == Dist.CLIENT) {
            modEventBus.addListener(this::onClientSetup);
        }
        modEventBus.addListener(this::onCommonSetup);

        var neoBus = NeoForge.EVENT_BUS;

        neoBus.addListener(this::registerBlockInteract);
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        if (runBlockStateTest) {
            event.enqueueWork(() -> {
                Map<String, Integer> stateCounts = new HashMap<>();
                int totalStates = 0;

                for (Block block : BuiltInRegistries.BLOCK) {
                    ResourceLocation id = BuiltInRegistries.BLOCK.getKey(block);
                    if (id.getNamespace().equals(YetAnotherFurniture.MOD_ID)) {
                        String className = block.getClass().getSimpleName();
                        int count = block.getStateDefinition().getPossibleStates().size();

                        stateCounts.merge(className, count, Integer::sum);
                        totalStates += count;
                    }
                }

                stateCounts.forEach((type, count) ->
                        System.out.println("  " + type + ": " + count + " states"));

                System.out.println("[YAF] Total BlockStates registered: " + totalStates);
            });
        }
    }

    public void onClientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(YetAnotherFurnitureClient::init);
    }

    public void registerBlockInteract(final PlayerInteractEvent.RightClickBlock event) {
        var result = ShelfInteractions.interact(event.getEntity(), event.getHand(), event.getHitVec());
        if (result.consumesAction()) {
            event.setCanceled(true);
        }
    }
}
