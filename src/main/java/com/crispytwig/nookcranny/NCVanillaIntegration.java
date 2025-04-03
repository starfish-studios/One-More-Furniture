package com.crispytwig.nookcranny;

import com.crispytwig.nookcranny.blocks.CurtainBlock;
import com.crispytwig.nookcranny.blocks.FanBlock;
import com.crispytwig.nookcranny.blocks.LampBlock;
import com.crispytwig.nookcranny.blocks.MailboxBlock;
import com.crispytwig.nookcranny.client.ChimeModel;
import com.crispytwig.nookcranny.client.FanModel;
import com.crispytwig.nookcranny.client.gui.screens.DrawerScreen;
import com.crispytwig.nookcranny.client.gui.screens.MailboxScreen;
import com.crispytwig.nookcranny.client.renderer.SeatRenderer;
import com.crispytwig.nookcranny.client.renderer.ShelfRenderer;
import com.crispytwig.nookcranny.client.renderer.blockentity.ChimeBlockEntityRenderer;
import com.crispytwig.nookcranny.client.renderer.blockentity.FanBlockEntityRenderer;
import com.crispytwig.nookcranny.client.renderer.blockentity.FlowerBasketRenderer;
import com.crispytwig.nookcranny.registry.NCBlockEntities;
import com.crispytwig.nookcranny.registry.NCBlocks;
import com.crispytwig.nookcranny.registry.NCEntities;
import com.crispytwig.nookcranny.registry.NCMenus;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.DyeColor;

import java.util.function.Supplier;

public class NCVanillaIntegration {

}
