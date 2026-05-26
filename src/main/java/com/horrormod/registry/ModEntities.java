package com.horrormod.registry;

import com.horrormod.HorrorMod;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = 
        DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, HorrorMod.MODID);

    public static final RegistryObject<EntityType<?>> SHADOW_MONSTER = ENTITIES.register(
        "shadow_monster",
        () -> EntityType.Builder.of((type, world) -> null, MobCategory.MONSTER)
            .sized(0.6f, 1.8f).build("shadow_monster"));
}
