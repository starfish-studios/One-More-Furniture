package com.crispytwig.nookcranny.blocks;

import com.crispytwig.nookcranny.blocks.properties.ColorList;
import com.crispytwig.nookcranny.blocks.properties.FoodList;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlateBlock extends Block {
    public static final EnumProperty<FoodList> FOOD = EnumProperty.create("food", FoodList.class);
    public static final VoxelShape AABB = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 1.0D, 14.0D);

    public PlateBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FOOD, FoodList.EMPTY));
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter world, BlockPos pos) {
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return AABB;
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateDefinition) {
        stateDefinition.add(FOOD);
    }

}
