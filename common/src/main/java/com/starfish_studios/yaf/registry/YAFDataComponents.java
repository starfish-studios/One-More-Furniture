package com.starfish_studios.yaf.registry;

import com.mojang.serialization.Codec;
import com.starfish_studios.yaf.YetAnotherFurniture;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.ExtraCodecs;

import java.util.function.UnaryOperator;

public class YAFDataComponents {

    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES = DeferredRegister.create(YetAnotherFurniture.MOD_ID, Registries.DATA_COMPONENT_TYPE);

    public static final RegistrySupplier<DataComponentType<String>> MAILBOX_STRING = register(
            "mailbox_string", () -> DataComponentType.<String>builder().persistent(Codec.STRING).build()
    );

    public static final RegistrySupplier<DataComponentType<Integer>> MAILBOX_TIMER = register(
            "mailbox_timer", () -> DataComponentType.<Integer>builder().persistent(Codec.INT).build()
    );

    private static <T> RegistrySupplier<DataComponentType<T>> register(String name, java.util.function.Supplier<DataComponentType<T>> supplier) {
        return DATA_COMPONENT_TYPES.register(name, supplier);
    }
}
