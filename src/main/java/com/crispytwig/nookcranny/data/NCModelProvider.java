package com.crispytwig.nookcranny.data;

import com.crispytwig.nookcranny.NookAndCranny;
import com.crispytwig.nookcranny.blocks.DrawerBlock;
import com.crispytwig.nookcranny.blocks.LampBlock;
import com.crispytwig.nookcranny.blocks.NightstandBlock;
import com.crispytwig.nookcranny.blocks.SofaBlock;
import com.crispytwig.nookcranny.blocks.properties.ColorList;
import com.crispytwig.nookcranny.blocks.properties.CountertopType;
import com.crispytwig.nookcranny.registry.NCBlocks;
import com.crispytwig.nookcranny.registry.NCItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.*;
import net.minecraft.data.models.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class NCModelProvider extends FabricModelProvider {

    public static final TextureSlot SIDES = TextureSlot.create("sides");
    public static final TextureSlot COUNTERTOP_SIDES = TextureSlot.create("countertop");
    public static final ModelTemplate DRAWER_CUBE_ORIENTABLE = createTemplate("drawer", TextureSlot.TOP, TextureSlot.FRONT, SIDES);
    public static final ModelTemplate DRAWER_CUBE_INVENTORY = createTemplate("drawer_inventory", TextureSlot.TOP, TextureSlot.FRONT, SIDES, COUNTERTOP_SIDES);
    public static final ModelTemplate COUNTERTOP = createTemplate("countertop", TextureSlot.TOP, SIDES);
    
    public static final TextureSlot CORE = TextureSlot.create("core");
    public static final TextureSlot BITS = TextureSlot.create("bits");

    public static final ModelTemplate NIGHTSTAND = createTemplate("nightstand", CORE, BITS, TextureSlot.PARTICLE);
    public static final ModelTemplate NIGHTSTAND_OPEN = createTemplate("nightstand_open", CORE, BITS, TextureSlot.PARTICLE);

    public static final ModelTemplate LAMP = createTemplate("lamp", TextureSlot.ALL, TextureSlot.PARTICLE);
    public static final ModelTemplate LAMP_BOTTOM = createTemplate("lamp_bottom", TextureSlot.ALL, TextureSlot.PARTICLE);
    public static final ModelTemplate LAMP_MIDDLE = createTemplate("lamp_middle", TextureSlot.ALL, TextureSlot.PARTICLE);
    public static final ModelTemplate LAMP_TOP = createTemplate("lamp_top", TextureSlot.ALL, TextureSlot.PARTICLE);
    public static final ModelTemplate LAMPSHADE = createTemplate("lampshade", TextureSlot.ALL, TextureSlot.PARTICLE);

    public static final ModelTemplate SOFA_SINGLE = createTemplate("sofa_single", TextureSlot.ALL, TextureSlot.PARTICLE);
    public static final ModelTemplate SOFA_LEFT = createTemplate("sofa_left", TextureSlot.ALL, TextureSlot.PARTICLE);
    public static final ModelTemplate SOFA_RIGHT = createTemplate("sofa_right", TextureSlot.ALL, TextureSlot.PARTICLE);
    public static final ModelTemplate SOFA_MIDDLE = createTemplate("sofa_middle", TextureSlot.ALL, TextureSlot.PARTICLE);
    public static final ModelTemplate SOFA_INNER = createTemplate("sofa_corner_inner", TextureSlot.ALL, TextureSlot.PARTICLE);
    public static final ModelTemplate SOFA_OUTER = createTemplate("sofa_corner_outer", TextureSlot.ALL, TextureSlot.PARTICLE);

    public NCModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generators) {

        for (CountertopType type : CountertopType.values()) {
            createCountertopType(generators, type);
        }

        for (Block block : NCBlocks.BLOCKS) {
            if (block instanceof SofaBlock) {
                createSofaBlock(generators, block);
            }
            if (block instanceof NightstandBlock) {
                createNightstandBlock(generators, block);
            }
            if (block instanceof DrawerBlock) {
                createDrawerBlock(generators, block);
            }
            if (block instanceof LampBlock) {
                //createLampBlock(generators, block);
            }
        }
    }

    private void createCountertopType(BlockModelGenerators generators, CountertopType type) {
        TextureMapping baseMapping = new TextureMapping()
                .put(TextureSlot.TOP, new ResourceLocation(NookAndCranny.MOD_ID, "block/drawers/" + type.getSerializedName() + "_drawer_top"))
                .put(SIDES, new ResourceLocation(NookAndCranny.MOD_ID, "block/drawers/countertop/" + type.getSerializedName() + "_drawer_countertop_sides"));

        COUNTERTOP.create(new ResourceLocation(NookAndCranny.MOD_ID, "block/drawers/countertop/countertop_" + type.getSerializedName()), baseMapping, generators.modelOutput);
    }

    @Override
    public void generateItemModels(ItemModelGenerators generators) {
        generators.generateFlatItem(NCItems.COPPER_SAW, ModelTemplates.FLAT_HANDHELD_ITEM);

        createDrawerItem(generators, NCItems.OAK_DRAWER, NCBlocks.OAK_DRAWER);
        createDrawerItem(generators, NCItems.SPRUCE_DRAWER, NCBlocks.SPRUCE_DRAWER);
        createDrawerItem(generators, NCItems.BIRCH_DRAWER, NCBlocks.BIRCH_DRAWER);
        createDrawerItem(generators, NCItems.JUNGLE_DRAWER, NCBlocks.JUNGLE_DRAWER);
        createDrawerItem(generators, NCItems.ACACIA_DRAWER, NCBlocks.ACACIA_DRAWER);
        createDrawerItem(generators, NCItems.MANGROVE_DRAWER, NCBlocks.MANGROVE_DRAWER);
        createDrawerItem(generators, NCItems.CRIMSON_DRAWER, NCBlocks.CRIMSON_DRAWER);
        createDrawerItem(generators, NCItems.DARK_OAK_DRAWER, NCBlocks.DARK_OAK_DRAWER);
        createDrawerItem(generators, NCItems.BAMBOO_DRAWER, NCBlocks.BAMBOO_DRAWER);
        createDrawerItem(generators, NCItems.WARPED_DRAWER, NCBlocks.WARPED_DRAWER);
        createDrawerItem(generators, NCItems.CHERRY_DRAWER, NCBlocks.CHERRY_DRAWER);
    }

    private void createNightstandBlock(BlockModelGenerators generators, Block nightstand) {

        MultiVariantGenerator multiVariant = MultiVariantGenerator.multiVariant(nightstand);

        var textMap = new TextureMapping()
                .put(CORE, getTexture(nightstand, "nightstand", "_core"))
                .put(BITS, getTexture(nightstand, "nightstand", "_bits"))
                .put(TextureSlot.PARTICLE, getTexture(nightstand, "nightstand", ""));

        ResourceLocation closed = NIGHTSTAND.create(nightstand, textMap, generators.modelOutput);
        ResourceLocation open = NIGHTSTAND_OPEN.createWithSuffix(nightstand, "_open", textMap, generators.modelOutput);

        multiVariant.with(BlockModelGenerators.createHorizontalFacingDispatch());
        multiVariant.with(PropertyDispatch.property(NightstandBlock.OPEN)
                .select(true, Variant.variant().with(VariantProperties.MODEL, open))
                .select(false, Variant.variant().with(VariantProperties.MODEL, closed)));

        generators.blockStateOutput.accept(multiVariant);
    }


    /**
     * Creates a reference to our own model templates
     */
    public static ModelTemplate createTemplate(String blockModelLocation, TextureSlot... requiredSlots) {
        return new ModelTemplate(Optional.of(
                new ResourceLocation(
                        NookAndCranny.MOD_ID,
                        "block/template/" + blockModelLocation
                )
        ),
                Optional.empty(),
                requiredSlots
        );
    }

    public final void createDrawerItem(ItemModelGenerators generators, Item item, Block block) {
        TextureMapping baseMapping = new TextureMapping()
                .put(TextureSlot.TOP, getTexture(block, "drawers", "_top"))
                .put(SIDES, getTexture(block, "drawers", "_side"))
                .put(TextureSlot.FRONT, getTexture(block, "drawers", "_front"))
                .put(COUNTERTOP_SIDES, getTexture(block, "drawers/countertop", "_countertop_sides"));

        DRAWER_CUBE_INVENTORY.create(ModelLocationUtils.getModelLocation(item), baseMapping, generators.output);
    }

    /**
     *
     * @param generators obligatory fabric wrapper
     * @param block the drawer to generate a model for
     */
    public final void createDrawerBlock(BlockModelGenerators generators, Block block) {
        TextureMapping baseMapping = new TextureMapping()
                .put(TextureSlot.TOP, getTexture(block, "drawers", "_top"))
                .put(SIDES, getTexture(block, "drawers", "_side"))
                .put(TextureSlot.FRONT, getTexture(block, "drawers", "_front"));

        ResourceLocation drawerId = DRAWER_CUBE_ORIENTABLE.create(block, baseMapping, generators.modelOutput);

        generators.skipAutoItemBlock(block);
        generators.blockStateOutput.accept(createDrawerMultipart(
                block,
                drawerId
        ));
    }

    public static BlockStateGenerator createDrawerMultipart(
            Block drawerBlock,
            ResourceLocation drawerId
    ) {
        MultiPartGenerator multiPart = MultiPartGenerator.multiPart(drawerBlock);

        for (CountertopType type : CountertopType.values()) {
            multiPart.with(
                    Condition.condition().term(DrawerBlock.COUNTERTOP, type),
                    Variant.variant().with(VariantProperties.MODEL, new ResourceLocation(NookAndCranny.MOD_ID, "block/drawers/countertop/countertop_"+type.getSerializedName()))
            );
        }

        for (Direction direction : Direction.Plane.HORIZONTAL) {
            multiPart.with(
                    Condition.condition().term(BlockStateProperties.HORIZONTAL_FACING, direction),
                    Variant.variant()
                            .with(VariantProperties.MODEL, drawerId)
                            .with(VariantProperties.Y_ROT, VariantProperties.Rotation.values()[((direction.get2DDataValue() * 90 + 180) / 90) % 4])
            );
        }

        return multiPart;
    }

    public static BlockStateGenerator createLampMultipart(
            Block lampBlock,
            Function<LampBlock.LampType, ResourceLocation> lampModelFunction,
            ResourceLocation lampId
    ) {
        MultiPartGenerator multiPart = MultiPartGenerator.multiPart(lampBlock);

        for (LampBlock.LampType type : LampBlock.LampType.values()) {
            multiPart.with(
                    Condition.condition().term(LampBlock.LAMP_TYPE, type),
                    Variant.variant().with(VariantProperties.MODEL, lampModelFunction.apply(type))
            );
        }

        for (ColorList color : ColorList.values()) {
            multiPart.with(
                    Condition.condition().term(LampBlock.LAMPSHADE, color),
                    Variant.variant()
                            .with(VariantProperties.MODEL, lampId)
            );
        }

        return multiPart;
    }

    public static ResourceLocation getTexture(Block block, String folder, String textureSuffix) {
        ResourceLocation resourceLocation = BuiltInRegistries.BLOCK.getKey(block);
        return resourceLocation.withPath((string2 -> "block/"+ folder + "/" + string2 + textureSuffix));
    }

    private void createSofaBlock(BlockModelGenerators generators, Block sofa) {
        MultiVariantGenerator multiVariant = MultiVariantGenerator.multiVariant(sofa);

        var textMap = new TextureMapping()
                .put(TextureSlot.ALL, getTexture(sofa, "sofa", ""))
                .put(TextureSlot.PARTICLE, getTexture(sofa, "sofa", ""));

        ResourceLocation single = SOFA_SINGLE.create(sofa, textMap, generators.modelOutput);
        ResourceLocation left = SOFA_LEFT.createWithSuffix(sofa, "_left", textMap, generators.modelOutput);
        ResourceLocation right = SOFA_RIGHT.createWithSuffix(sofa, "_right", textMap, generators.modelOutput);
        ResourceLocation middle = SOFA_MIDDLE.createWithSuffix(sofa, "_middle", textMap, generators.modelOutput);
        ResourceLocation inner = SOFA_INNER.createWithSuffix(sofa, "_inner", textMap, generators.modelOutput);
        ResourceLocation outer = SOFA_OUTER.createWithSuffix(sofa, "_outer", textMap, generators.modelOutput);

        Map<SofaBlock.SofaShape, ResourceLocation> shapeModels = Map.of(
                SofaBlock.SofaShape.SINGLE, single,
                SofaBlock.SofaShape.LEFT, left,
                SofaBlock.SofaShape.RIGHT, right,
                SofaBlock.SofaShape.MIDDLE, middle,
                SofaBlock.SofaShape.INNER_LEFT, inner,
                SofaBlock.SofaShape.INNER_RIGHT, inner,
                SofaBlock.SofaShape.OUTER_LEFT, outer,
                SofaBlock.SofaShape.OUTER_RIGHT, outer
        );

        PropertyDispatch.C2<SofaBlock.SofaShape, Direction> dispatch = PropertyDispatch.properties(SofaBlock.SHAPE, BlockStateProperties.HORIZONTAL_FACING);

        for (var entry : shapeModels.entrySet()) {
            SofaBlock.SofaShape shape = entry.getKey();
            ResourceLocation model = entry.getValue();

            for (Direction direction : Direction.Plane.HORIZONTAL) {
                VariantProperties.Rotation rotation = getRotation(direction, shape);

                dispatch.select(shape, direction, Variant.variant()
                        .with(VariantProperties.Y_ROT, rotation)
                        .with(VariantProperties.MODEL, model));
            }
        }

        multiVariant.with(dispatch);

        generators.blockStateOutput.accept(multiVariant);
    }

    private static VariantProperties.@NotNull Rotation getRotation(Direction direction, SofaBlock.SofaShape shape) {
        VariantProperties.Rotation rotation = switch (direction) {
            case EAST -> VariantProperties.Rotation.R90;
            case SOUTH -> VariantProperties.Rotation.R180;
            case WEST -> VariantProperties.Rotation.R270;
            default -> VariantProperties.Rotation.R0;
        };

        //Special case since we have the same model for left and right
        if (shape == SofaBlock.SofaShape.INNER_RIGHT) {
            rotation = switch (direction) {
                case NORTH -> VariantProperties.Rotation.R90;
                case EAST -> VariantProperties.Rotation.R180;
                case SOUTH -> VariantProperties.Rotation.R270;
                default -> VariantProperties.Rotation.R0;
            };
        }

        //Special case since we have the same model for left and right
        if (shape == SofaBlock.SofaShape.OUTER_LEFT) {
            rotation = switch (direction) {
                case NORTH -> VariantProperties.Rotation.R270;
                case SOUTH -> VariantProperties.Rotation.R90;
                case WEST -> VariantProperties.Rotation.R180;
                default -> VariantProperties.Rotation.R0;
            };
        }
        return rotation;
    }

    private void createLampBlock(BlockModelGenerators generators, Block lamp) {

        var textMap = new TextureMapping()
                .put(TextureSlot.ALL, getTexture(lamp, "lamp", ""))
                .put(TextureSlot.PARTICLE, getTexture(lamp, "lamp", ""));

        ResourceLocation bottom = LAMP_BOTTOM.createWithSuffix(lamp,"_bottom", textMap, generators.modelOutput);
        ResourceLocation middle = LAMP_MIDDLE.createWithSuffix(lamp, "_middle", textMap, generators.modelOutput);
        ResourceLocation top = LAMP_TOP.createWithSuffix(lamp, "_top", textMap, generators.modelOutput);
        ResourceLocation single = LAMP.createWithSuffix(lamp, "", textMap, generators.modelOutput);
        ResourceLocation shade = LAMPSHADE.createWithSuffix(lamp, "_shade", textMap, generators.modelOutput);

        ResourceLocation lampId = LAMP.create(lamp, textMap, generators.modelOutput);

        generators.blockStateOutput.accept(createLampMultipart(
                lamp,
                type -> LAMP.createWithSuffix(lamp, "_type", textMap, generators.modelOutput),
                lampId
        ));
    }

}