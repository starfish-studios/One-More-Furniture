package com.crispytwig.omf.fabric.data;

import com.crispytwig.omf.OneMoreFurniture;
import com.crispytwig.omf.block.*;
import com.crispytwig.omf.block.properties.ChairType;
import com.crispytwig.omf.block.properties.ColorList;
import com.crispytwig.omf.block.properties.CountertopType;
import com.crispytwig.omf.registry.OMFBlocks;
import com.crispytwig.omf.registry.OMFItems;
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
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.SlabType;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Function;

public class OMFModelProvider extends FabricModelProvider {

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

    public static final ModelTemplate CURTAIN_SINGLE_CLOSED = createTemplate("curtain_single_closed", TextureSlot.ALL, TextureSlot.PARTICLE);
    public static final ModelTemplate CURTAIN_BOTTOM_MIDDLE_CLOSED = createTemplate("curtain_bottom_middle_closed", TextureSlot.ALL, TextureSlot.PARTICLE);

    public static final ModelTemplate CURTAIN_SINGLE_OPEN = createTemplate("curtain_single_open", TextureSlot.ALL, TextureSlot.PARTICLE);
    public static final ModelTemplate CURTAIN_LEFT_OPEN = createTemplate("curtain_left_open", TextureSlot.ALL, TextureSlot.PARTICLE);
    public static final ModelTemplate CURTAIN_RIGHT_OPEN = createTemplate("curtain_right_open", TextureSlot.ALL, TextureSlot.PARTICLE);
    public static final ModelTemplate CURTAIN_BOTTOM_LEFT_OPEN = createTemplate("curtain_bottom_left_open", TextureSlot.ALL, TextureSlot.PARTICLE);
    public static final ModelTemplate CURTAIN_BOTTOM_RIGHT_OPEN = createTemplate("curtain_bottom_right_open", TextureSlot.ALL, TextureSlot.PARTICLE);
    public static final ModelTemplate CURTAIN_BOTTOM_MIDDLE_OPEN = createTemplate("curtain_bottom_middle_open", TextureSlot.ALL, TextureSlot.PARTICLE);
    public static final ModelTemplate CURTAIN_LEFT_CORNER_OPEN = createTemplate("curtain_left_corner_open", TextureSlot.ALL, TextureSlot.PARTICLE);
    public static final ModelTemplate CURTAIN_RIGHT_CORNER_OPEN = createTemplate("curtain_right_corner_open", TextureSlot.ALL, TextureSlot.PARTICLE);
    public static final ModelTemplate CURTAIN_TOP_OPEN = createTemplate("curtain_top_open", TextureSlot.ALL, TextureSlot.PARTICLE);
    public static final ModelTemplate CURTAIN_TOP_SINGLE_OPEN = createTemplate("curtain_top_single_open", TextureSlot.ALL, TextureSlot.PARTICLE);

    public static final ModelTemplate WIND_CHIME = createTemplate("chime", TextureSlot.ALL);
    public static final ModelTemplate FLOWER_BASKET = createTemplate("flower_basket", TextureSlot.ALL, TextureSlot.DIRT, TextureSlot.PARTICLE);
    public static final ModelTemplate FAN = createTemplate("ceiling_fan", TextureSlot.ALL);


    public OMFModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generators) {
        for (CountertopType type : CountertopType.values()) {
            createCountertopType(generators, type);
        }

        OMFBlocks.FLOWER_BASKETS.forEach((omfWoodType, supplier) -> createFlowerBasket(generators, supplier.get()));
        OMFBlocks.CURTAINS.forEach((omfWoodType, supplier) -> createCurtainBlock(generators, supplier.get()));
        OMFBlocks.SOFAS.forEach((omfWoodType, supplier) -> createSofaBlock(generators, supplier.get()));
        OMFBlocks.DRAWERS.forEach((omfWoodType, supplier) -> createDrawerBlock(generators, supplier.get()));
        OMFBlocks.CABINET.forEach((omfWoodType, supplier) -> createCabinetBlock(generators, supplier.get()));
        OMFBlocks.SHELVES.forEach((omfWoodType, supplier) -> createShelfBlock(generators, supplier.get()));
        OMFBlocks.CHAIRS.forEach((omfWoodType, supplier) -> createChairBlock(generators, supplier.get()));

        createWindChimeBlock(generators, OMFBlocks.AMETHYST_WIND_CHIMES.get());
        createWindChimeBlock(generators, OMFBlocks.BAMBOO_WIND_CHIMES.get());
        createWindChimeBlock(generators, OMFBlocks.BAMBOO_STRIPPED_WIND_CHIMES.get());
        createWindChimeBlock(generators, OMFBlocks.BONE_WIND_CHIMES.get());
        createWindChimeBlock(generators, OMFBlocks.COPPER_WIND_CHIMES.get());
        createWindChimeBlock(generators, OMFBlocks.ECHO_SHARD_WIND_CHIMES.get());

    }

    private void createWindChimeBlock(BlockModelGenerators generators, Block block){
        TextureMapping one = new TextureMapping().put(TextureSlot.ALL, getTexture(block, "wind_chime", ""));

        var res = WIND_CHIME.create(block, one, generators.modelOutput);
        MultiVariantGenerator multiVariant = MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, res));

        multiVariant.with(BlockModelGenerators.createHorizontalFacingDispatch());
        generators.blockStateOutput.accept(multiVariant);
    }

    private void createFlowerBasket(BlockModelGenerators generators, Block block) {
        TextureMapping one = new TextureMapping()
                .put(TextureSlot.ALL, getTexture(block, "flower_basket", ""))
                .put(TextureSlot.PARTICLE, getTexture(block, "flower_basket", ""))
                .put(TextureSlot.DIRT, new ResourceLocation("block/dirt"));

        var res = FLOWER_BASKET.create(block, one, generators.modelOutput);
        MultiVariantGenerator multiVariant = MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, res));

        multiVariant.with(BlockModelGenerators.createHorizontalFacingDispatch());
        generators.blockStateOutput.accept(multiVariant);
    }

    public record OpenClosed(ResourceLocation open, ResourceLocation closed){}


    private PropertyDispatch.C2<Boolean, CurtainBlock.CurtainShape> createCurtainPropertyDispatch(Map<CurtainBlock.CurtainShape, OpenClosed> curtainMappings) {
        PropertyDispatch.C2<Boolean, CurtainBlock.CurtainShape> dispatch = PropertyDispatch.properties(CurtainBlock.OPEN, CurtainBlock.SHAPE);

        for (Map.Entry<CurtainBlock.CurtainShape, OpenClosed> entry : curtainMappings.entrySet()) {
            CurtainBlock.CurtainShape shape = entry.getKey();
            OpenClosed models = entry.getValue();
            dispatch = dispatch.select(true, shape, Variant.variant().with(VariantProperties.MODEL, models.open))
                    .select(false, shape, Variant.variant().with(VariantProperties.MODEL, models.closed));
        }

        return dispatch;
    }

    private void createCurtainBlock(BlockModelGenerators generators, Block block) {
        Map<CurtainBlock.CurtainShape, OpenClosed> curtainMappings = new HashMap<>();

        var bottomMiddleAndMiddleOpen = new TextureMapping().put(TextureSlot.ALL, new ResourceLocation(OneMoreFurniture.MOD_ID, "block/curtain/empty"));
        var bottomRightAndBottomLeftOpen = new TextureMapping().put(TextureSlot.ALL, getTexture(block, "curtain", "_bottom_open"));
        var cornerRightAndCornerLeftOpen = new TextureMapping().put(TextureSlot.ALL, getTexture(block, "curtain", "_corner_open"));
        var rightAndLeftOpen = new TextureMapping().put(TextureSlot.ALL, getTexture(block, "curtain", "_open"));
        var topAndBottomSingleOpen = new TextureMapping().put(TextureSlot.ALL, getTexture(block, "curtain", "_top_single_open"));

        var singleClosed = new TextureMapping().put(TextureSlot.ALL, getTexture(block, "curtain", "_single_closed"));
        var singleOpen = new TextureMapping().put(TextureSlot.ALL, getTexture(block, "curtain", "_single_open"));
        var bottomClosedAndTopOpen = new TextureMapping().put(TextureSlot.ALL, getTexture(block, "curtain", "_top_open"));

        ResourceLocation bottomMiddleOpenResource = CURTAIN_BOTTOM_MIDDLE_OPEN.createWithSuffix(block, "_middle_open", bottomMiddleAndMiddleOpen, generators.modelOutput);
        ResourceLocation bottomMiddleClosedResource = CURTAIN_BOTTOM_MIDDLE_CLOSED.createWithSuffix(block, "_closed", bottomClosedAndTopOpen, generators.modelOutput);

        ResourceLocation bottomLeftOpenResource = CURTAIN_BOTTOM_LEFT_OPEN.createWithSuffix(block, "_bottom_left_open", bottomRightAndBottomLeftOpen, generators.modelOutput);
        ResourceLocation bottomRightOpenResource = CURTAIN_BOTTOM_RIGHT_OPEN.createWithSuffix(block, "_bottom_right_open", bottomRightAndBottomLeftOpen, generators.modelOutput);

        ResourceLocation cornerLeftOpenResource = CURTAIN_LEFT_CORNER_OPEN.createWithSuffix(block, "_left_corner_open", cornerRightAndCornerLeftOpen, generators.modelOutput);
        ResourceLocation cornerRightOpenResource = CURTAIN_RIGHT_CORNER_OPEN.createWithSuffix(block, "_right_corner_open", cornerRightAndCornerLeftOpen, generators.modelOutput);
        ResourceLocation defaultClosed = CURTAIN_SINGLE_CLOSED.createWithSuffix(block, "_single_closed", singleClosed, generators.modelOutput);
        ResourceLocation singleOpenResource = CURTAIN_SINGLE_OPEN.createWithSuffix(block, "_single_open", singleOpen, generators.modelOutput);

        ResourceLocation topOpenResource = CURTAIN_TOP_OPEN.createWithSuffix(block, "_top_open", bottomClosedAndTopOpen, generators.modelOutput);
        ResourceLocation topSingleOpenResource = CURTAIN_TOP_SINGLE_OPEN.createWithSuffix(block, "_top_single_open", topAndBottomSingleOpen, generators.modelOutput);

        ResourceLocation leftOpenResource = CURTAIN_LEFT_OPEN.createWithSuffix(block, "_left_open", rightAndLeftOpen, generators.modelOutput);
        ResourceLocation rightOpenResource = CURTAIN_RIGHT_OPEN.createWithSuffix(block, "_right_open", rightAndLeftOpen, generators.modelOutput);


        curtainMappings.put(CurtainBlock.CurtainShape.BOTTOM_MIDDLE, new OpenClosed(bottomMiddleOpenResource, bottomMiddleClosedResource));
        curtainMappings.put(CurtainBlock.CurtainShape.BOTTOM_LEFT, new OpenClosed(bottomLeftOpenResource, bottomMiddleClosedResource));
        curtainMappings.put(CurtainBlock.CurtainShape.BOTTOM_RIGHT, new OpenClosed(bottomRightOpenResource, bottomMiddleClosedResource));
        curtainMappings.put(CurtainBlock.CurtainShape.CORNER_RIGHT, new OpenClosed(cornerRightOpenResource, defaultClosed));
        curtainMappings.put(CurtainBlock.CurtainShape.CORNER_LEFT, new OpenClosed(cornerLeftOpenResource, defaultClosed));
        curtainMappings.put(CurtainBlock.CurtainShape.TOP, new OpenClosed(topOpenResource, defaultClosed));
        curtainMappings.put(CurtainBlock.CurtainShape.TOP_SINGLE, new OpenClosed(topSingleOpenResource, defaultClosed));
        curtainMappings.put(CurtainBlock.CurtainShape.LEFT, new OpenClosed(leftOpenResource, defaultClosed));
        curtainMappings.put(CurtainBlock.CurtainShape.RIGHT, new OpenClosed(rightOpenResource, defaultClosed));
        curtainMappings.put(CurtainBlock.CurtainShape.MIDDLE, new OpenClosed(bottomMiddleOpenResource, defaultClosed));
        curtainMappings.put(CurtainBlock.CurtainShape.SINGLE, new OpenClosed(topSingleOpenResource, defaultClosed));
        curtainMappings.put(CurtainBlock.CurtainShape.BOTTOM_SINGLE, new OpenClosed(singleOpenResource, bottomMiddleClosedResource));


        MultiVariantGenerator multiVariant = MultiVariantGenerator.multiVariant(block);
        multiVariant.with(BlockModelGenerators.createHorizontalFacingDispatch());
        multiVariant.with(createCurtainPropertyDispatch(curtainMappings));

        generators.skipAutoItemBlock(block);
        generators.blockStateOutput.accept(multiVariant);
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
                .put(TextureSlot.TOP, new ResourceLocation(OneMoreFurniture.MOD_ID, "block/drawers/" + type.getSerializedName() + "_drawer_top"))
                .put(SIDES, new ResourceLocation(OneMoreFurniture.MOD_ID, "block/drawers/countertop/" + type.getSerializedName() + "_drawer_countertop_sides"));
        COUNTERTOP.create(new ResourceLocation(OneMoreFurniture.MOD_ID, "block/drawers/countertop/countertop_" + type.getSerializedName()), baseMapping, generators.modelOutput);

        COUNTERTOP_BOTTOM.create(new ResourceLocation(OneMoreFurniture.MOD_ID, "block/drawers/countertop/countertop_" + type.getSerializedName() + "_bottom"), baseMapping, generators.modelOutput);
    }

    @Override
    public void generateItemModels(ItemModelGenerators generators) {

        generators.generateFlatItem(OMFItems.COPPER_SAW.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        OMFBlocks.DRAWERS.forEach(((omfWoodType, supplier) -> createDrawerItem(generators, supplier.get().asItem(), supplier.get())));
        OMFBlocks.CHAIRS.forEach(((omfWoodType, supplier) -> createChairItem(generators, supplier.get().asItem(), supplier.get())));
        OMFBlocks.CABINET.forEach(((omfWoodType, supplier) -> createCabinetItem(generators, supplier.get().asItem(), supplier.get())));
        OMFItems.FANS.forEach(((omfWoodType, supplier) ->  generators.generateFlatItem(supplier.get(), ModelTemplates.FLAT_ITEM)));

    }

    /**
     * Creates a reference to our own model templates
     */
    public static ModelTemplate createTemplate(String blockModelLocation, TextureSlot... requiredSlots) {
        return new ModelTemplate(Optional.of(
                new ResourceLocation(
                        OneMoreFurniture.MOD_ID,
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

    public final void createCabinetItem(ItemModelGenerators generators, Item item, Block block) {
        String wood = BuiltInRegistries.BLOCK.getKey(block).getPath();
        String firstName = wood.split("_cabinet")[0];
        ResourceLocation loc = new ResourceLocation(OneMoreFurniture.MOD_ID, "block/drawers/" + firstName + "_drawer_top");
        ResourceLocation countertopLoc = new ResourceLocation(OneMoreFurniture.MOD_ID, "block/drawers/countertop/" + firstName + "_drawer_countertop_sides");

        TextureMapping baseMapping = new TextureMapping()
                .put(TextureSlot.TOP, loc)
                .put(SIDES, getTexture(block, "cabinet", "_sides"))
                .put(TextureSlot.FRONT, getTexture(block, "cabinet", "_front"))
                .put(COUNTERTOP_SIDES, countertopLoc);
        CABINET_INVENTORY.create(ModelLocationUtils.getModelLocation(item), baseMapping, generators.output);
    }

    public final void createCabinetBlock(BlockModelGenerators generators, Block block) {
        String wood = BuiltInRegistries.BLOCK.getKey(block).getPath();
        String firstName = wood.split("_cabinet")[0];
        ResourceLocation loc = new ResourceLocation(OneMoreFurniture.MOD_ID, "block/drawers/" + firstName + "_drawer_top");

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
                        Condition.condition()
                                .term(DrawerBlock.COUNTERTOP, type)
                                .term(CabinetBlock.BOTTOM, bl),
                        Variant.variant()
                                .with(VariantProperties.MODEL, new ResourceLocation(OneMoreFurniture.MOD_ID, string))
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
                    Variant.variant().with(VariantProperties.MODEL, new ResourceLocation(OneMoreFurniture.MOD_ID, "block/drawers/countertop/countertop_" + type.getSerializedName()))
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

    /* Removed
    public static BlockStateGenerator createChairTuckableMultipart(
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

                ResourceLocation tuckedModel = new ResourceLocation(OneMoreFurniture.MOD_ID, "block/" + "acacia_chair_" + type.getSerializedName().toLowerCase() + "_tucked");

                multiPart.with(
                        Condition.condition()
                                .term(BlockStateProperties.HORIZONTAL_FACING, direction)
                                .term(ChairBlock.BACK, true)
                                .term(ChairBlock.BACK_TYPE, type)
                                .term(TuckableBlock.TUCKED, false),
                        Variant.variant()
                                .with(VariantProperties.MODEL, backModel)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.values()[yRotation / 90])
                );

                multiPart.with(
                        Condition.condition()
                                .term(BlockStateProperties.HORIZONTAL_FACING, direction)
                                .term(ChairBlock.BACK, true)
                                .term(ChairBlock.BACK_TYPE, type)
                                .term(TuckableBlock.TUCKED, true),
                        Variant.variant()
                                .with(VariantProperties.MODEL, tuckedModel)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.values()[yRotation / 90])
                );
            }

            multiPart.with(
                    Condition.condition()
                            .term(BlockStateProperties.HORIZONTAL_FACING, direction)
                            .term(ChairBlock.BACK, false)
                            .term(TuckableBlock.TUCKED, false),
                    Variant.variant()
                            .with(VariantProperties.MODEL, chairBacklessId)
                            .with(VariantProperties.Y_ROT, VariantProperties.Rotation.values()[yRotation / 90])
            );

            ResourceLocation backlessTuckedModel = new ResourceLocation(chairBacklessId.getNamespace(), chairBacklessId.getPath() + "_tucked");

            multiPart.with(
                    Condition.condition()
                            .term(BlockStateProperties.HORIZONTAL_FACING, direction)
                            .term(ChairBlock.BACK, false)
                            .term(TuckableBlock.TUCKED, true),
                    Variant.variant()
                            .with(VariantProperties.MODEL, backlessTuckedModel)
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

     */

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
            cushionModels.put(color, new ResourceLocation(OneMoreFurniture.MOD_ID, "block/chair/cushion/" + color + "_cushion"));
        }
        String chairBaseName = BuiltInRegistries.BLOCK.getKey(chair).getPath();
        Map<ChairType, ResourceLocation> backTypeModels = new HashMap<>();
        for (ChairType type : ChairType.values()) {
            ModelTemplate chairTemplate = createTemplate(
                    "chair_" + type.getSerializedName().toLowerCase(),
                    TextureSlot.ALL
            );
            chairTemplate.createWithSuffix(chair, "_" + type.getSerializedName().toLowerCase(), baseMapping, generators.modelOutput);
            ResourceLocation chairTypeId = new ResourceLocation(OneMoreFurniture.MOD_ID, "block/" + chairBaseName + "_" + type.getSerializedName().toLowerCase());
            backTypeModels.put(type, chairTypeId);
        }
        ResourceLocation chairBacklessId = CHAIR_BACKLESS.createWithSuffix(chair, "_backless", baseMapping, generators.modelOutput);
        generators.skipAutoItemBlock(chair);
        generators.blockStateOutput.accept(createChairMultipart(chair, chairBacklessId, cushionModels, backTypeModels));
    }

    /* removed
    private void createChairTuckableBlock(BlockModelGenerators generators, Block chair) {
        TextureMapping baseMapping = new TextureMapping()
                .put(TextureSlot.ALL, getTexture(chair, "chair", ""));
        Map<ColorList, ResourceLocation> cushionModels = new HashMap<>();
        for (ColorList color : ColorList.values()) {
            cushionModels.put(color, new ResourceLocation(OneMoreFurniture.MOD_ID, "block/chair/cushion/" + color + "_cushion"));
        }
        String chairBaseName = BuiltInRegistries.BLOCK.getKey(chair).getPath();
        Map<ChairType, ResourceLocation> backTypeModels = new HashMap<>();

        Set<ResourceLocation> generatedModels = new HashSet<>();

        for (ChairType type : ChairType.values()) {
            ResourceLocation chairTypeId = new ResourceLocation(OneMoreFurniture.MOD_ID, "block/" + chairBaseName + "_" + type.getSerializedName().toLowerCase());

            if (!generatedModels.contains(chairTypeId)) {
                ModelTemplate chairTemplate = createTemplate(
                        "chair_" + type.getSerializedName().toLowerCase(),
                        TextureSlot.ALL
                );
                chairTemplate.createWithSuffix(chair, "_" + type.getSerializedName().toLowerCase(), baseMapping, generators.modelOutput);
                generatedModels.add(chairTypeId);
            }

            ResourceLocation chairTypeTuckedId = new ResourceLocation(OneMoreFurniture.MOD_ID, "block/" + chairBaseName + "_" + type.getSerializedName().toLowerCase() + "_tucked");
            if (!generatedModels.contains(chairTypeTuckedId)) {
                ModelTemplate chairTemplateTucked = createTemplate(
                        "chair_" + type.getSerializedName().toLowerCase() + "_tucked",
                        TextureSlot.ALL
                );
                chairTemplateTucked.createWithSuffix(chair, "_" + type.getSerializedName().toLowerCase() + "_tucked", baseMapping, generators.modelOutput);
                generatedModels.add(chairTypeTuckedId);
            }

            backTypeModels.put(type, chairTypeId);
        }

        ResourceLocation chairBacklessId = new ResourceLocation(OneMoreFurniture.MOD_ID, "block/" + chairBaseName + "_backless");
        if (!generatedModels.contains(chairBacklessId)) {
            CHAIR_BACKLESS.createWithSuffix(chair, "_backless", baseMapping, generators.modelOutput);
            generatedModels.add(chairBacklessId);
        }

        ResourceLocation chairBacklessTuckedId = new ResourceLocation(OneMoreFurniture.MOD_ID, "block/" + chairBaseName + "_backless_tucked");
        if (!generatedModels.contains(chairBacklessTuckedId)) {
            CHAIR_BACKLESS.createWithSuffix(chair, "_backless_tucked", baseMapping, generators.modelOutput);
            generatedModels.add(chairBacklessTuckedId);
        }

        for (ChairType type : ChairType.values()) {
            ResourceLocation chairBackTypeTuckedId = new ResourceLocation(OneMoreFurniture.MOD_ID, "block/" + chairBaseName + "_" + type.getSerializedName().toLowerCase() + "_tucked");

            if (!generatedModels.contains(chairBackTypeTuckedId)) {
                backTypeModels.put(type, new ResourceLocation(OneMoreFurniture.MOD_ID, "block/" + chairBaseName + "_" + type.getSerializedName().toLowerCase() + "_tucked"));
                generatedModels.add(chairBackTypeTuckedId);
            }
        }

        generators.skipAutoItemBlock(chair);
        generators.blockStateOutput.accept(createChairMultipart(chair, chairBacklessId, cushionModels, backTypeModels));
    }

     */

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

    public static ResourceLocation getTexture(Block block, String folder, String textureSuffix) {
        ResourceLocation resourceLocation = BuiltInRegistries.BLOCK.getKey(block);
        return resourceLocation.withPath((string2 -> "block/"+ folder + "/" + string2 + textureSuffix));
    }
}
