package com.horrormod.nightmare;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import java.util.*;

public class NightmareHandler {
    private static final Random RANDOM = new Random();
    private static final double NIGHTMARE_CHANCE = 0.35;

    private static final ResourceKey<Level>[] NIGHTMARES = new ResourceKey[]{
        ResourceKey.create(net.minecraft.core.registries.Registries.DIMENSION, new ResourceLocation("horrormod", "school_nightmare")),
        ResourceKey.create(net.minecraft.core.registries.Registries.DIMENSION, new ResourceLocation("horrormod", "office_nightmare")),
        ResourceKey.create(net.minecraft.core.registries.Registries.DIMENSION, new ResourceLocation("horrormod", "prank_nightmare")),
        ResourceKey.create(net.minecraft.core.registries.Registries.DIMENSION, new ResourceLocation("horrormod", "burning_room")),
        ResourceKey.create(net.minecraft.core.registries.Registries.DIMENSION, new ResourceLocation("horrormod", "daniil_death"))
    };

    private static final String[] NIGHTMARE_NAMES = {
        "§8Кошмар в школе", "§0Офис", "§dРозыгрыш", "§cГорящая комната", "§4Смерть Даниила"
    };

    @SubscribeEvent
    public void onSleep(PlayerSleepInBedEvent event) {
        if (event.getEntity().level().isClientSide()) return;
        Player player = event.getEntity();

        if (RANDOM.nextDouble() < NIGHTMARE_CHANCE) {
            event.setResult(Player.BedSleepingProblem.OTHER_PROBLEM);
            player.displayClientMessage(Component.literal("§4Что-то не так... Холод пронизывает тело..."), true);

            ServerPlayer sp = (ServerPlayer) player;
            int idx = RANDOM.nextInt(NIGHTMARES.length);
            ResourceKey<Level> target = NIGHTMARES[idx];

            sp.getServer().execute(() -> {
                try { Thread.sleep(3000); } catch (InterruptedException ignored) {}
                ServerLevel world = sp.getServer().getLevel(target);
                if (world != null) {
                    sp.displayClientMessage(Component.literal(NIGHTMARE_NAMES[idx]), true);
                    sp.teleportTo(world, sp.getX(), 64, sp.getZ(), sp.getYRot(), sp.getXRot());
                }
            });
        }
    }
}
