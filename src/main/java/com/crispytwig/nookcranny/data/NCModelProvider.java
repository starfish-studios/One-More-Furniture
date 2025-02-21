package com.crispytwig.nookcranny.data;

import com.crispytwig.nookcranny.NookAndCranny;
import com.crispytwig.nookcranny.blocks.DrawerBlock;
import com.crispytwig.nookcranny.blocks.SofaBlock;
import com.crispytwig.nookcranny.blocks.properties.CountertopType;
import com.crispytwig.nookcranny.registry.NCBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.*;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.Optional;
import java.util.function.Function;

public class NCModelProvider extends FabricModelProvider {

    public static final TextureSlot SIDES = TextureSlot.create("sides");
    public static final ModelTemplate DRAWER_CUBE_ORIENTABLE = createTemplate("drawer", TextureSlot.TOP, TextureSlot.FRONT, SIDES);
    public static final ModelTemplate DRAWER_COUNTER_TOP_ORIENTABLE = createTemplate("countertop", TextureSlot.TOP, SIDES, TextureSlot.PARTICLE);


    public static final ModelTemplate SOFA_SINGLE = createTemplate("sofa_single", TextureSlot.ALL, TextureSlot.PARTICLE);
    public static final ModelTemplate SOFA_LEFT = createTemplate("sofa_left", TextureSlot.ALL, TextureSlot.PARTICLE);
    public static final ModelTemplate SOFA_RIGHT = createTemplate("sofa_right", TextureSlot.ALL, TextureSlot.PARTICLE);
    public static final ModelTemplate SOFA_MIDDLE = createTemplate("sofa_middle", TextureSlot.ALL, TextureSlot.PARTICLE);
    public static final ModelTemplate SOFA_CORNER = createTemplate("sofa_corner", TextureSlot.ALL, TextureSlot.PARTICLE);
    public static final ModelTemplate SOFA_CORNER_OUTER = createTemplate("sofa_corner_outer", TextureSlot.ALL, TextureSlot.PARTICLE);

    public NCModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generators) {
        createDrawerBlock(generators, NCBlocks.OAK_DRAWER);

        for (Block block : NCBlocks.BLOCKS) {
            if (block instanceof SofaBlock) {
                createSofaBlock(generators, block);
            }
        }
    }


    @Override
    public void generateItemModels(ItemModelGenerators generators) {

    }

    private void createSofaBlock(BlockModelGenerators generators, Block sofa) {

        MultiVariantGenerator multiVariant = MultiVariantGenerator.multiVariant(sofa);

        var textMap = new TextureMapping()
                .put(TextureSlot.ALL, getTexture(sofa, "sofa", ""))
                .put(TextureSlot.PARTICLE, getTexture(sofa, "sofa", ""));

        ResourceLocation single = SOFA_SINGLE.create(sofa, textMap, generators.modelOutput);
        ResourceLocation left = SOFA_LEFT.createWithSuffix(sofa, "_left", textMap, generators.modelOutput);
        ResourceLocation right = SOFA_RIGHT.createWithSuffix(sofa, "_right",textMap, generators.modelOutput);
        ResourceLocation middle = SOFA_MIDDLE.createWithSuffix(sofa, "_middle",textMap, generators.modelOutput);
        ResourceLocation corner = SOFA_CORNER.createWithSuffix(sofa, "_corner",textMap, generators.modelOutput);
        ResourceLocation outerCorner = SOFA_CORNER_OUTER.createWithSuffix(sofa, "_corner_outer",textMap, generators.modelOutput);

        multiVariant.with(BlockModelGenerators.createHorizontalFacingDispatch());
        multiVariant.with(
                PropertyDispatch.property(SofaBlock.SHAPE)
                        .select(SofaBlock.SofaShape.SINGLE, Variant.variant().with(VariantProperties.MODEL, single))
                        .select(SofaBlock.SofaShape.LEFT, Variant.variant().with(VariantProperties.MODEL, left))
                        .select(SofaBlock.SofaShape.RIGHT, Variant.variant().with(VariantProperties.MODEL, right))
                        .select(SofaBlock.SofaShape.MIDDLE, Variant.variant().with(VariantProperties.MODEL, middle))
                        .select(SofaBlock.SofaShape.CORNER, Variant.variant().with(VariantProperties.MODEL, corner))
                        .select(SofaBlock.SofaShape.CORNER_OUTER, Variant.variant().with(VariantProperties.MODEL, outerCorner))
        );

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

    /**
     *
     * @param generators obligatory fabric wrapper
     * @param block the drawer to generate a model for
     */
    public final void createDrawerBlock(BlockModelGenerators generators, Block block) {
        TextureMapping baseMapping = new TextureMapping()
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top"))
                .put(SIDES, getTexture(block, "drawer", "_side"));

        TextureMapping drawerTextureMapping = baseMapping.put(
                TextureSlot.FRONT, getTexture(block, "drawer", "_front")
        );
        TextureMapping drawerTextureMapping2 = baseMapping.put(
                TextureSlot.PARTICLE,  getTexture(block, "drawer", "_side")
        );

        ResourceLocation drawerId = DRAWER_CUBE_ORIENTABLE.create(block, drawerTextureMapping, generators.modelOutput);

        generators.blockStateOutput.accept(createDrawerMultipart(
                block,
                type -> DRAWER_COUNTER_TOP_ORIENTABLE.createWithSuffix(block, "_countertop_" + type.getSerializedName(), drawerTextureMapping2, generators.modelOutput),
                drawerId
        ));
    }


    public static BlockStateGenerator createDrawerMultipart(
            Block drawerBlock,
            Function<CountertopType, ResourceLocation> counterTopModelFunction,
            ResourceLocation drawerId
    ) {
        MultiPartGenerator multiPart = MultiPartGenerator.multiPart(drawerBlock);

        for (CountertopType type : CountertopType.values()) {
            multiPart.with(
                    Condition.condition().term(DrawerBlock.COUNTERTOP, type),
                    Variant.variant().with(VariantProperties.MODEL, counterTopModelFunction.apply(type))
            );
        }

        for (Direction direction : Direction.Plane.HORIZONTAL) {
            multiPart.with(
                    Condition.condition().term(BlockStateProperties.HORIZONTAL_FACING, direction),
                    Variant.variant()
                            .with(VariantProperties.MODEL, drawerId)
                            .with(VariantProperties.Y_ROT, VariantProperties.Rotation.values()[direction.get2DDataValue() * 90 / 90])
            );
        }

        return multiPart;
    }

    public static ResourceLocation getTexture(Block block, String folder, String textureSuffix) {
        ResourceLocation resourceLocation = BuiltInRegistries.BLOCK.getKey(block);
        return resourceLocation.withPath((string2 -> "block/"+ folder + "/" + string2 + textureSuffix));
    }
}