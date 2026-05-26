package com.horrormod;

import com.horrormod.nightmare.NightmareHandler;
import com.horrormod.registry.ModEntities;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(HorrorMod.MODID)
public class HorrorMod {
    public static final String MODID = "horrormod";

    public HorrorMod() {
        var bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModEntities.ENTITIES.register(bus);
        MinecraftForge.EVENT_BUS.register(new NightmareHandler());
    }
}
