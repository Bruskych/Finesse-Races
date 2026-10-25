package org.bruskych.finesse_races.core.other;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import org.bruskych.finesse_races.common.network.FRNetwork;
import org.bruskych.finesse_races.common.network.OpenRaceScreenS2CPacket;
import org.bruskych.finesse_races.core.FinesseRaces;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;
import org.bruskych.finesse_races.gameplay.races.core.RaceManager;
import static org.bruskych.finesse_races.core.FinesseRaces.LOGGER;

/**
 * Main Forge event listener.
 * Acts as a bridge between the game and the mod logic,
 * processing server-side player ticks and filtering out the client side
 * to prevent desynchronization.
 */
@Mod.EventBusSubscriber(modid = FinesseRaces.MOD_ID)
public class FREvents {

    // Server starting event
    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("The server starts up alongside Finesse Races.");
    }

    // Triggered once when a player logs into the server
    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            RaceManager.loadPlayerRace(serverPlayer);

            // Синхронизируем стартовые наборы перед открытием экрана выбора расы
            FRNetwork.sendToPlayer(
                    new org.bruskych.finesse_races.common.network.SyncStarterKitsS2CPacket(
                            org.bruskych.finesse_races.gameplay.starter.StarterKitManager.getAllKitsForSync()
                    ),
                    serverPlayer
            );

            // Если у игрока уже была выбрана раса ранее (например, старый мир), помечаем что стартовый набор не нужен
            if (RaceManager.getRaceOfPlayer(serverPlayer) != null) {
                serverPlayer.getPersistentData().putBoolean(
                        org.bruskych.finesse_races.gameplay.starter.StarterKitHandler.NBT_STARTER_KIT_GIVEN, true
                );
            } else {
                FRNetwork.sendToPlayer(new OpenRaceScreenS2CPacket(), serverPlayer);
            }
        }
    }

    // Triggered once when a player logs out of the server
    @SubscribeEvent
    public static void onPlayerLeave(PlayerEvent.PlayerLoggedOutEvent event) {
        RaceManager.unloadPlayerRace(event.getEntity());
    }

    // Triggers when the player dies or moves between dimensions
    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        String oldRaceId = event.getOriginal().getPersistentData().getString("finesse_race_id");
        if (!oldRaceId.isEmpty()) {
            event.getEntity().getPersistentData().putString("finesse_race_id", oldRaceId);
        }
        // Сохраняем флаг выданного стартового набора после смерти или перехода между измерениями
        boolean kitGiven = event.getOriginal().getPersistentData().getBoolean(
                org.bruskych.finesse_races.gameplay.starter.StarterKitHandler.NBT_STARTER_KIT_GIVEN
        );
        event.getEntity().getPersistentData().putBoolean(
                org.bruskych.finesse_races.gameplay.starter.StarterKitHandler.NBT_STARTER_KIT_GIVEN, kitGiven
        );

        RaceManager.loadPlayerRace(event.getEntity());
    }

    // Checks player state. Called every tick (20 times per second)
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }
        Player player = event.player;
        if (!player.level().isClientSide()) {
            AbstractRace currentRace = RaceManager.getRaceOfPlayer(player);
            if (currentRace != null) {
                currentRace.onPlayerTick(player);
            }
        }
    }

    @SubscribeEvent
    public static void onAddReloadListeners( net.minecraftforge.event.AddReloadListenerEvent event) {
        event.addListener(org.bruskych.finesse_races.gameplay.starter.StarterKitManager.INSTANCE);
    }

    @SubscribeEvent
    public static void onDatapackSync(net.minecraftforge.event.OnDatapackSyncEvent event) {
        var packet = new org.bruskych.finesse_races.common.network.SyncStarterKitsS2CPacket(
                org.bruskych.finesse_races.gameplay.starter.StarterKitManager.getAllKitsForSync()
        );
        if (event.getPlayer() != null) {
            FRNetwork.sendToPlayer(packet, event.getPlayer());
        } else {
            for (ServerPlayer player : event.getPlayerList().getPlayers()) {
                FRNetwork.sendToPlayer(packet, player);
            }
        }
    }

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        org.bruskych.finesse_races.common.commands.StarterKitCommand.register(event.getDispatcher());
    }
}
