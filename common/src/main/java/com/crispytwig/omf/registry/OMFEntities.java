package com.crispytwig.omf.registry;

import com.crispytwig.omf.OneMoreFurniture;
import com.crispytwig.omf.entities.SeatEntity;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.*;

import java.util.function.Supplier;

public class OMFEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(OneMoreFurniture.MOD_ID, Registries.ENTITY_TYPE);


    public static final RegistrySupplier<EntityType<?>> SEAT = ENTITY_TYPES.register("seat", () ->
            EntityType.Builder.of(
                    SeatEntity::new,
                    MobCategory.MISC)
                    .sized(0f,0f
                    ).build("seat"));

}
