package com.crispytwig.nookcranny.data;

import com.crispytwig.nookcranny.NookAndCranny;
import com.crispytwig.nookcranny.blocks.*;
import com.crispytwig.nookcranny.blocks.properties.ChairType;
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
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.SlabType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class NCModelProvider extends FabricModelProvider {

    public static final TextureSlot SIDES = TextureSlot.create("sides");
    public static final TextureSlot COUNTERTOP_SIDES = TextureSlot.create("countertop");
    public static final ModelTemplate DRAWER_CUBE_ORIENTABLE = createTemplate("drawer", TextureSlot.TOP, TextureSlot.FRONT, SIDES, TextureSlot.PARTICLE);
    public static final ModelTemplate DRAWER_CUBE_INVENTORY = createTemplate("drawer_inventory", TextureSlot.TOP, TextureSlot.FRONT, SIDES, COUNTERTOP_SIDES);
    public static final ModelTemplate COUNTERTOP = createTemplate("countertop", TextureSlot.TOP, SIDES);
    public static final ModelTemplate COUNTERTOP_BOTTOM = createTemplate("countertop_bottom", TextureSlot.TOP, SIDES);

    public static final ModelTemplate CABINET = createTemplate("cabinet", TextureSlot.TOP, TextureSlot.FRONT, SIDES, TextureSlot.PARTICLE);
    public static final ModelTemplate CABINET_TOP = createTemplate("cabinet_top", TextureSlot.TOP, TextureSlot.FRONT, SIDES, TextureSlot.PARTICLE);
    public static final ModelTemplate CABINET_INVENTORY = createTemplate("cabinet_inventory", TextureSlot.TOP, TextureSlot.FRONT, SIDES, COUNTERTOP_SIDES);


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

    public static final ModelTemplate SHELF_BOTTOM_CEILING = createTemplate("shelf/shelf_ceiling_bottom", TextureSlot.ALL, TextureSlot.PARTICLE);
    public static final ModelTemplate SHELF_DOUBLE_CEILING = createTemplate("shelf/shelf_ceiling_double", TextureSlot.ALL, TextureSlot.PARTICLE);
    public static final ModelTemplate SHELF_TOP_CEILING = createTemplate("shelf/shelf_ceiling_top", TextureSlot.ALL, TextureSlot.PARTICLE);

    public static final ModelTemplate SHELF_DOUBLE_FLOOR = createTemplate("shelf/shelf_floor_double", TextureSlot.ALL, TextureSlot.PARTICLE);
    public static final ModelTemplate SHELF_BOTTOM_FLOOR = createTemplate("shelf/shelf_floor_bottom", TextureSlot.ALL, TextureSlot.PARTICLE);
    public static final ModelTemplate SHELF_TOP_FLOOR = createTemplate("shelf/shelf_floor_top", TextureSlot.ALL, TextureSlot.PARTICLE);

    public static final ModelTemplate SHELF_BOTTOM_SINGLE = createTemplate("shelf/shelf_wall_bottom", TextureSlot.ALL, TextureSlot.PARTICLE);
    public static final ModelTemplate SHELF_DOUBLE_SINGLE = createTemplate("shelf/shelf_wall_double", TextureSlot.ALL, TextureSlot.PARTICLE);
    public static final ModelTemplate SHELF_TOP_SINGLE = createTemplate("shelf/shelf_wall_top", TextureSlot.ALL, TextureSlot.PARTICLE);

    public static final ModelTemplate CHAIR_BACKLESS = createTemplate("chair_backless", TextureSlot.ALL);
    public static final ModelTemplate CHAIR_ITEM = createTemplate("chair_1", TextureSlot.ALL);

    public static final ModelTemplate CURTAIN_SINGLE_CLOSED = createTemplate("curtain_single_closed", TextureSlot.TEXTURE);
    public static final ModelTemplate CURTAIN_LEFT_CLOSED = createTemplate("curtain_left_closed", TextureSlot.TEXTURE);
    public static final ModelTemplate CURTAIN_RIGHT_CLOSED = createTemplate("curtain_right_closed", TextureSlot.TEXTURE);
    public static final ModelTemplate CURTAIN_MIDDLE_CLOSED = createTemplate("curtain_middle_closed", TextureSlot.TEXTURE);
    public static final ModelTemplate CURTAIN_BOTTOM_SINGLE_CLOSED = createTemplate("curtain_bottom_single_closed", TextureSlot.TEXTURE);
    public static final ModelTemplate CURTAIN_BOTTOM_LEFT_CLOSED = createTemplate("curtain_bottom_left_closed", TextureSlot.TEXTURE);
    public static final ModelTemplate CURTAIN_BOTTOM_RIGHT_CLOSED = createTemplate("curtain_bottom_right_closed", TextureSlot.TEXTURE);
    public static final ModelTemplate CURTAIN_BOTTOM_MIDDLE_CLOSED = createTemplate("curtain_bottom_middle_closed", TextureSlot.TEXTURE);
    public static final ModelTemplate CURTAIN_LEFT_CORNER_CLOSED = createTemplate("curtain_left_corner_closed", TextureSlot.TEXTURE);
    public static final ModelTemplate CURTAIN_RIGHT_CORNER_CLOSED = createTemplate("curtain_right_corner_closed", TextureSlot.TEXTURE);

    public static final ModelTemplate CURTAIN_SINGLE_OPEN = createTemplate("curtain_single_open", TextureSlot.TEXTURE);
    public static final ModelTemplate CURTAIN_LEFT_OPEN = createTemplate("curtain_left_open", TextureSlot.TEXTURE);
    public static final ModelTemplate CURTAIN_RIGHT_OPEN = createTemplate("curtain_right_open", TextureSlot.TEXTURE);
    public static final ModelTemplate CURTAIN_MIDDLE_OPEN = createTemplate("curtain_middle_open", TextureSlot.TEXTURE);
    public static final ModelTemplate CURTAIN_BOTTOM_SINGLE_OPEN = createTemplate("curtain_bottom_single_open", TextureSlot.TEXTURE);
    public static final ModelTemplate CURTAIN_BOTTOM_LEFT_OPEN = createTemplate("curtain_bottom_left_open", TextureSlot.TEXTURE);
    public static final ModelTemplate CURTAIN_BOTTOM_RIGHT_OPEN = createTemplate("curtain_bottom_right_open", TextureSlot.TEXTURE);
    public static final ModelTemplate CURTAIN_BOTTOM_MIDDLE_OPEN = createTemplate("curtain_bottom_middle_open", TextureSlot.TEXTURE);
    public static final ModelTemplate CURTAIN_LEFT_CORNER_OPEN = createTemplate("curtain_left_corner_open", TextureSlot.TEXTURE);
    public static final ModelTemplate CURTAIN_RIGHT_CORNER_OPEN = createTemplate("curtain_right_corner_open", TextureSlot.TEXTURE);

    public static final ModelTemplate WIND_CHIME = createTemplate("chime", TextureSlot.ALL);
    public static final ModelTemplate FAN = createTemplate("ceiling_fan", TextureSlot.ALL);


    public NCModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generators) {
        for (CountertopType type : CountertopType.values()) {
            createCountertopType(generators, type);
        }
        for (Block block : NCBlocks.BLOCKS) {
            if (block instanceof CurtainBlock) {
                //TODO createCurtainBlock(generators, block);
            }
            if (block instanceof SofaBlock) {
                createSofaBlock(generators, block);
            }
            if (block instanceof NightstandBlock) {
                createNightstandBlock(generators, block);
            }
            if (block instanceof DrawerBlock) {
                createDrawerBlock(generators, block);
            }
            if (block instanceof CabinetBlock) {
                createCabinetBlock(generators, block);
            }
            if (block instanceof LampBlock) {
                //createLampBlock(generators, block);
            }
            if (block instanceof ShelfBlock) {
                createShelfBlock(generators, block);
            }
            if (block instanceof ChairBlock) {
                createChairBlock(generators, block);
            }

            if (block instanceof WindChimeBlock) {
                TextureMapping one = new TextureMapping().put(TextureSlot.ALL, getTexture(block, "wind_chime", ""));

                var res = WIND_CHIME.create(block, one, generators.modelOutput);
                MultiVariantGenerator multiVariant = MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, res));

                multiVariant.with(BlockModelGenerators.createHorizontalFacingDispatch());
                generators.blockStateOutput.accept(multiVariant);
            }

            if (block instanceof FanBlock) {
                //createFan(generators, block);
            }
        }
    }

    private void createFan(BlockModelGenerators generators, Block block) {
        ResourceLocation r = ModelLocationUtils.getModelLocation(block);
        ResourceLocation r2 = ModelLocationUtils.getModelLocation(block, "_on");
        generators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block)
                .with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.POWERED, r, r2))
                .with(BlockModelGenerators.createFacingDispatch()));
    }

    private void createCurtainBlock(BlockModelGenerators generators, Block block) {
        TextureMapping closedMapping = new TextureMapping()
                .put(TextureSlot.ALL, new ResourceLocation("nookcranny", "block/curtain/white_curtain"))
                .put(TextureSlot.PARTICLE, new ResourceLocation("nookcranny", "block/curtain/white_curtain"));

        TextureMapping openSideMapping = new TextureMapping()
                .put(TextureSlot.ALL, new ResourceLocation("nookcranny", "block/curtain/white_curtain_side_open"))
                .put(TextureSlot.PARTICLE, new ResourceLocation("nookcranny", "block/curtain/white_curtain_side_open"));

        TextureMapping openCenterMapping = new TextureMapping()
                .put(TextureSlot.ALL, new ResourceLocation("nookcranny", "block/curtain/white_curtain_empty"))
                .put(TextureSlot.PARTICLE, new ResourceLocation("nookcranny", "block/curtain/white_curtain_empty"));

        ResourceLocation closedSingle = createTemplate("white_curtain_single_closed", TextureSlot.ALL, TextureSlot.PARTICLE)
                .create(block, closedMapping, generators.modelOutput);
        ResourceLocation closedLeft = createTemplate("white_curtain_left_closed", TextureSlot.ALL, TextureSlot.PARTICLE)
                .create(block, closedMapping, generators.modelOutput);
        ResourceLocation closedCenter = createTemplate("white_curtain_center_closed", TextureSlot.ALL, TextureSlot.PARTICLE)
                .create(block, closedMapping, generators.modelOutput);
        ResourceLocation closedRight = createTemplate("white_curtain_right_closed", TextureSlot.ALL, TextureSlot.PARTICLE)
                .create(block, closedMapping, generators.modelOutput);

        ResourceLocation openSingle = createTemplate("white_curtain_single_open", TextureSlot.ALL, TextureSlot.PARTICLE)
                .create(block, openCenterMapping, generators.modelOutput);
        ResourceLocation openLeft = createTemplate("white_curtain_left_open", TextureSlot.ALL, TextureSlot.PARTICLE)
                .create(block, openSideMapping, generators.modelOutput);
        ResourceLocation openCenter = createTemplate("white_curtain_center_open", TextureSlot.ALL, TextureSlot.PARTICLE)
                .create(block, openCenterMapping, generators.modelOutput);
        ResourceLocation openRight = createTemplate("white_curtain_right_open", TextureSlot.ALL, TextureSlot.PARTICLE)
                .create(block, openSideMapping, generators.modelOutput);
    }

    private void createShelfBlock(BlockModelGenerators generators, Block block) {
        MultiVariantGenerator multiVariant = MultiVariantGenerator.multiVariant(block);
        var textMap = new TextureMapping()
                .put(TextureSlot.ALL, getTexture(block, "shelf", ""))
                .put(TextureSlot.PARTICLE, getTexture(block, "shelf", ""));
        ResourceLocation ceilingBottom = SHELF_BOTTOM_CEILING.createWithSuffix(block, "_ceiling_bottom", textMap, generators.modelOutput);
        ResourceLocation ceilingDouble = SHELF_DOUBLE_CEILING.createWithSuffix(block, "_ceiling_double", textMap, generators.modelOutput);
        ResourceLocation ceilingTop = SHELF_TOP_CEILING.createWithSuffix(block, "_ceiling_top", textMap, generators.modelOutput);
        ResourceLocation floorDouble = SHELF_DOUBLE_FLOOR.createWithSuffix(block, "_floor_double", textMap, generators.modelOutput);
        ResourceLocation floorBottom = SHELF_BOTTOM_FLOOR.createWithSuffix(block, "_floor_bottom", textMap, generators.modelOutput);
        ResourceLocation floorTop = SHELF_TOP_FLOOR.createWithSuffix(block, "_floor_top", textMap, generators.modelOutput);
        ResourceLocation shelfBottom = SHELF_BOTTOM_SINGLE.createWithSuffix(block, "_wall_bottom", textMap, generators.modelOutput);
        ResourceLocation shelfDouble = SHELF_DOUBLE_SINGLE.createWithSuffix(block, "_wall_double", textMap, generators.modelOutput);
        ResourceLocation shelfTop = SHELF_TOP_SINGLE.createWithSuffix(block, "_wall_top", textMap, generators.modelOutput);
        multiVariant.with(BlockModelGenerators.createHorizontalFacingDispatch());
        multiVariant.with(PropertyDispatch.properties(ShelfBlock.FACE, ShelfBlock.HALF)
                .select(AttachFace.CEILING, SlabType.TOP, Variant.variant().with(VariantProperties.MODEL, ceilingTop))
                .select(AttachFace.CEILING, SlabType.BOTTOM, Variant.variant().with(VariantProperties.MODEL, ceilingBottom))
                .select(AttachFace.CEILING, SlabType.DOUBLE, Variant.variant().with(VariantProperties.MODEL, ceilingDouble))
                .select(AttachFace.FLOOR, SlabType.TOP, Variant.variant().with(VariantProperties.MODEL, floorTop))
                .select(AttachFace.FLOOR, SlabType.BOTTOM, Variant.variant().with(VariantProperties.MODEL, floorBottom))
                .select(AttachFace.FLOOR, SlabType.DOUBLE, Variant.variant().with(VariantProperties.MODEL, floorDouble))
                .select(AttachFace.WALL, SlabType.TOP, Variant.variant().with(VariantProperties.MODEL, shelfTop))
                .select(AttachFace.WALL, SlabType.BOTTOM, Variant.variant().with(VariantProperties.MODEL, shelfBottom))
                .select(AttachFace.WALL, SlabType.DOUBLE, Variant.variant().with(VariantProperties.MODEL, shelfDouble))
        );
        generators.skipAutoItemBlock(block);
        generators.blockStateOutput.accept(multiVariant);
    }

    private void createCountertopType(BlockModelGenerators generators, CountertopType type) {
        TextureMapping baseMapping = new TextureMapping()
                .put(TextureSlot.TOP, new ResourceLocation(NookAndCranny.MOD_ID, "block/drawers/" + type.getSerializedName() + "_drawer_top"))
                .put(SIDES, new ResourceLocation(NookAndCranny.MOD_ID, "block/drawers/countertop/" + type.getSerializedName() + "_drawer_countertop_sides"));
        COUNTERTOP.create(new ResourceLocation(NookAndCranny.MOD_ID, "block/drawers/countertop/countertop_" + type.getSerializedName()), baseMapping, generators.modelOutput);

        COUNTERTOP_BOTTOM.create(new ResourceLocation(NookAndCranny.MOD_ID, "block/drawers/countertop/countertop_" + type.getSerializedName() + "_bottom"), baseMapping, generators.modelOutput);
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
        createChairItem(generators, NCItems.OAK_CHAIR, NCBlocks.OAK_CHAIR);
        createChairItem(generators, NCItems.SPRUCE_CHAIR, NCBlocks.SPRUCE_CHAIR);
        createChairItem(generators, NCItems.BIRCH_CHAIR, NCBlocks.BIRCH_CHAIR);
        createChairItem(generators, NCItems.JUNGLE_CHAIR, NCBlocks.JUNGLE_CHAIR);
        createChairItem(generators, NCItems.ACACIA_CHAIR, NCBlocks.ACACIA_CHAIR);
        createChairItem(generators, NCItems.MANGROVE_CHAIR, NCBlocks.MANGROVE_CHAIR);
        createChairItem(generators, NCItems.CRIMSON_CHAIR, NCBlocks.CRIMSON_CHAIR);
        createChairItem(generators, NCItems.DARK_OAK_CHAIR, NCBlocks.DARK_OAK_CHAIR);
        createChairItem(generators, NCItems.BAMBOO_CHAIR, NCBlocks.BAMBOO_CHAIR);
        createChairItem(generators, NCItems.WARPED_CHAIR, NCBlocks.WARPED_CHAIR);
        createChairItem(generators, NCItems.CHERRY_CHAIR, NCBlocks.CHERRY_CHAIR);
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

    public final void createChairItem(ItemModelGenerators generators, Item item, Block block) {
        TextureMapping baseMapping = new TextureMapping().put(TextureSlot.ALL, getTexture(block, "chair", ""));
        ResourceLocation resourceLocation = BuiltInRegistries.ITEM.getKey(item);
        resourceLocation = resourceLocation.withPrefix("item/");
        CHAIR_ITEM.create(resourceLocation, baseMapping, generators.output);
    }

    public final void createDrawerBlock(BlockModelGenerators generators, Block block) {
        TextureMapping baseMapping = new TextureMapping()
                .put(TextureSlot.TOP, getTexture(block, "drawers", "_top"))
                .put(SIDES, getTexture(block, "drawers", "_side"))
                .put(TextureSlot.FRONT, getTexture(block, "drawers", "_front"))
                .put(TextureSlot.PARTICLE, getTexture(block, "drawers", "_front"));
        ResourceLocation drawerId = DRAWER_CUBE_ORIENTABLE.create(block, baseMapping, generators.modelOutput);
        generators.skipAutoItemBlock(block);
        generators.blockStateOutput.accept(createDrawerMultipart(
                block,
                drawerId
        ));
    }

    public final void createCabinetBlock(BlockModelGenerators generators, Block block) {
        String wood = BuiltInRegistries.BLOCK.getKey(block).getPath();
        String firstName = wood.split("_cabinet")[0];
        ResourceLocation loc = new ResourceLocation(NookAndCranny.MOD_ID, "block/drawers/" + firstName + "_drawer_front");

        TextureMapping baseMapping = new TextureMapping()
                .put(TextureSlot.TOP, loc)
                .put(SIDES, getTexture(block, "cabinet", "_sides"))
                .put(TextureSlot.FRONT, getTexture(block, "cabinet", "_front"))
                .put(TextureSlot.PARTICLE, getTexture(block, "cabinet", "_front"));

        TextureMapping upperMapping = new TextureMapping()
                .put(TextureSlot.TOP, loc)
                .put(SIDES, getTexture(block, "cabinet", "_sides_upper"))
                .put(TextureSlot.FRONT, getTexture(block, "cabinet", "_front_upper"))
                .put(TextureSlot.PARTICLE, getTexture(block, "cabinet", "_front_upper"));
        ResourceLocation cabinetId = CABINET.create(block, baseMapping, generators.modelOutput);
        ResourceLocation cabinetTopId = CABINET_TOP.createWithSuffix(block, "_bottom", upperMapping, generators.modelOutput);

        generators.skipAutoItemBlock(block);
        generators.blockStateOutput.accept(createCabinetMultipart(
                block,
                cabinetId,
                cabinetTopId
        ));
    }

    public static BlockStateGenerator createCabinetMultipart(
            Block drawerBlock,
            ResourceLocation cabinetId,
            ResourceLocation cabinetTopId
    ) {
        MultiPartGenerator multiPart = MultiPartGenerator.multiPart(drawerBlock);

        for (boolean bl : new boolean[]{true, false}) {

            for (CountertopType type : CountertopType.values()) {
                var string = bl ? "block/drawers/countertop/countertop_" + type.getSerializedName() :
                        "block/drawers/countertop/countertop_" + type.getSerializedName() + "_bottom";
                multiPart.with(
                        Condition.condition().term(DrawerBlock.COUNTERTOP, type),
                        Variant.variant().with(VariantProperties.MODEL, new ResourceLocation(NookAndCranny.MOD_ID, string))
                );
            }
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                multiPart.with(
                        Condition.condition()
                                .term(BlockStateProperties.BOTTOM, bl)
                                .term(BlockStateProperties.HORIZONTAL_FACING, direction),
                        Variant.variant()
                                .with(VariantProperties.MODEL, bl ? cabinetId : cabinetTopId)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.values()[((direction.get2DDataValue() * 90 + 180) / 90) % 4])
                );
            }
        }

        return multiPart;
    }

    public static BlockStateGenerator createDrawerMultipart(
            Block drawerBlock,
            ResourceLocation drawerId
    ) {
        MultiPartGenerator multiPart = MultiPartGenerator.multiPart(drawerBlock);
        for (CountertopType type : CountertopType.values()) {
            multiPart.with(
                    Condition.condition().term(DrawerBlock.COUNTERTOP, type),
                    Variant.variant().with(VariantProperties.MODEL, new ResourceLocation(NookAndCranny.MOD_ID, "block/drawers/countertop/countertop_" + type.getSerializedName()))
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

    public static BlockStateGenerator createChairMultipart(
            Block chairBlock,
            ResourceLocation chairBacklessId,
            Map<ColorList, ResourceLocation> cushionModels,
            Map<ChairType, ResourceLocation> backTypeModels
    ) {
        MultiPartGenerator multiPart = MultiPartGenerator.multiPart(chairBlock);
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            int yRotation = ((direction.get2DDataValue() * 90 + 180) % 360);
            for (Map.Entry<ChairType, ResourceLocation> entry : backTypeModels.entrySet()) {
                ChairType type = entry.getKey();
                ResourceLocation backModel = entry.getValue();
                multiPart.with(
                        Condition.condition()
                                .term(BlockStateProperties.HORIZONTAL_FACING, direction)
                                .term(ChairBlock.BACK, true)
                                .term(ChairBlock.BACK_TYPE, type),
                        Variant.variant()
                                .with(VariantProperties.MODEL, backModel)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.values()[yRotation / 90])
                );
            }
            multiPart.with(
                    Condition.condition()
                            .term(BlockStateProperties.HORIZONTAL_FACING, direction)
                            .term(ChairBlock.BACK, false),
                    Variant.variant()
                            .with(VariantProperties.MODEL, chairBacklessId)
                            .with(VariantProperties.Y_ROT, VariantProperties.Rotation.values()[yRotation / 90])
            );
        }
        for (Map.Entry<ColorList, ResourceLocation> entry : cushionModels.entrySet()) {
            if (entry.getKey() == ColorList.EMPTY) continue;
            ColorList cushionColor = entry.getKey();
            ResourceLocation cushionModel = entry.getValue();
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                int yRotation = ((direction.get2DDataValue() * 90 + 180) % 360);
                multiPart.with(
                        Condition.condition()
                                .term(BlockStateProperties.HORIZONTAL_FACING, direction)
                                .term(ChairBlock.CUSHION, cushionColor),
                        Variant.variant()
                                .with(VariantProperties.MODEL, cushionModel)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.values()[yRotation / 90])
                );
            }
        }
        return multiPart;
    }

    private void createChairBlock(BlockModelGenerators generators, Block chair) {
        TextureMapping baseMapping = new TextureMapping()
                .put(TextureSlot.ALL, getTexture(chair, "chair", ""));
        Map<ColorList, ResourceLocation> cushionModels = new HashMap<>();
        for (ColorList color : ColorList.values()) {
            cushionModels.put(color, new ResourceLocation(NookAndCranny.MOD_ID, "block/chair/cushion/" + color + "_cushion"));
        }
        String chairBaseName = BuiltInRegistries.BLOCK.getKey(chair).getPath();
        Map<ChairType, ResourceLocation> backTypeModels = new HashMap<>();
        for (ChairType type : ChairType.values()) {
            ModelTemplate chairTemplate = createTemplate(
                    "chair_" + type.getSerializedName().toLowerCase(),
                    TextureSlot.ALL
            );
            chairTemplate.createWithSuffix(chair, "_" + type.getSerializedName().toLowerCase(), baseMapping, generators.modelOutput);
            ResourceLocation chairTypeId = new ResourceLocation(NookAndCranny.MOD_ID, "block/" + chairBaseName + "_" + type.getSerializedName().toLowerCase());
            backTypeModels.put(type, chairTypeId);
        }
        ResourceLocation chairBacklessId = CHAIR_BACKLESS.createWithSuffix(chair, "_backless", baseMapping, generators.modelOutput);
        generators.skipAutoItemBlock(chair);
        generators.blockStateOutput.accept(createChairMultipart(chair, chairBacklessId, cushionModels, backTypeModels));
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
        if (shape == SofaBlock.SofaShape.INNER_RIGHT) {
            rotation = switch (direction) {
                case NORTH -> VariantProperties.Rotation.R90;
                case EAST -> VariantProperties.Rotation.R180;
                case SOUTH -> VariantProperties.Rotation.R270;
                default -> VariantProperties.Rotation.R0;
            };
        }
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

    public static ResourceLocation getTexture(Block block, String folder, String textureSuffix) {
        ResourceLocation resourceLocation = BuiltInRegistries.BLOCK.getKey(block);
        return resourceLocation.withPath((string2 -> "block/"+ folder + "/" + string2 + textureSuffix));
    }
}
