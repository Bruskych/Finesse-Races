package org.bruskych.finesse_races.core.events;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.OnDatapackSyncEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import org.bruskych.finesse_races.common.commands.StarterKitCommand;
import org.bruskych.finesse_races.common.network.FRNetwork;
import org.bruskych.finesse_races.common.network.OpenRaceScreenS2CPacket;
import org.bruskych.finesse_races.common.network.SyncStarterKitsS2CPacket;
import org.bruskych.finesse_races.core.FinesseRaces;
import org.bruskych.finesse_races.gameplay.conditions.PlayerConditionHandler;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;
import org.bruskych.finesse_races.gameplay.races.core.RaceManager;
import org.bruskych.finesse_races.gameplay.starter.StarterKitHandler;
import org.bruskych.finesse_races.gameplay.starter.StarterKitManager;

import static org.bruskych.finesse_races.core.FinesseRaces.LOGGER;

/**
 * Main Forge event listener.
 * Acts as a bridge between the game and the mod logic,
 * processing server-side player ticks and filtering out the client side
 * to prevent desynchronization.
 */
@Mod.EventBusSubscriber(modid = FinesseRaces.MOD_ID)
public class FRServerEvents {

    /**
     * Triggered when the server is starting up.
     */
    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("The server starts up alongside Finesse Races.");
    }

    /**
     * Triggered when a player logs into the server.
     * Synchronizes starter kits and opens the race selection screen if needed.
     */
    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            RaceManager.loadPlayerRace(serverPlayer);

            // Sync starter kits before opening the race selection screen
            FRNetwork.sendToPlayer(new SyncStarterKitsS2CPacket(StarterKitManager.getAllKitsForSync()), serverPlayer);

            if (RaceManager.getRaceOfPlayer(serverPlayer) != null) {
                serverPlayer.getPersistentData().putBoolean(StarterKitHandler.NBT_STARTER_KIT_GIVEN, true);
            } else {
                // Set the selecting state explicitly when forcing the screen open on join
                PlayerConditionHandler.setSelectingRace(serverPlayer, true);
                FRNetwork.sendToPlayer(new OpenRaceScreenS2CPacket(), serverPlayer);
            }
        }
    }

    /**
     * Triggered when a player disconnects from the server.
     * Removes the selecting state and unloads race data.
     */
    @SubscribeEvent
    public static void onPlayerLeave(PlayerEvent.PlayerLoggedOutEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            PlayerConditionHandler.setSelectingRace(player, false);
            RaceManager.unloadPlayerRace(player);
        }
    }

    /**
     * Triggered when a player dies or moves between dimensions.
     * Transfers race data and starter kit flags to the new player entity.
     */
    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        String oldRaceId = event.getOriginal().getPersistentData().getString("finesse_race_id");
        if (!oldRaceId.isEmpty()) {
            event.getEntity().getPersistentData().putString("finesse_race_id", oldRaceId);
        }

        boolean kitGiven = event.getOriginal().getPersistentData().getBoolean(StarterKitHandler.NBT_STARTER_KIT_GIVEN);
        event.getEntity().getPersistentData().putBoolean(StarterKitHandler.NBT_STARTER_KIT_GIVEN, kitGiven);

        RaceManager.loadPlayerRace(event.getEntity());
    }

    /**
     * Called every tick for every player (20 times per second).
     * Handles race passive abilities and prevents drowning/burning while selecting a race.
     */
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        // Execute logic only at the end of the tick on the logical server
        if (event.phase != TickEvent.Phase.END || event.player.level().isClientSide()) {
            return;
        }

        Player player = event.player;

        // If the player is currently selecting a race, protect them and skip ability ticks
        if (PlayerConditionHandler.isSelectingRace(player)) {
            player.setAirSupply(player.getMaxAirSupply());
            if (player.isOnFire()) {
                player.clearFire();
            }
            return; // Skip race ability logic while in the selection screen
        }

        // Apply passive race abilities
        AbstractRace currentRace = RaceManager.getRaceOfPlayer(player);
        if (currentRace != null) {
            currentRace.onPlayerTick(player);
        }
    }

    /**
     * Triggered when data packs are reloaded (e.g., /reload command).
     */
    @SubscribeEvent
    public static void onAddReloadListeners(AddReloadListenerEvent event) {
        event.addListener(StarterKitManager.INSTANCE);
    }

    /**
     * Triggered when data packs are synchronized with clients.
     */
    @SubscribeEvent
    public static void onDatapackSync(OnDatapackSyncEvent event) {
        var packet = new SyncStarterKitsS2CPacket(StarterKitManager.getAllKitsForSync());
        if (event.getPlayer() != null) {
            FRNetwork.sendToPlayer(packet, event.getPlayer());
        } else {
            for (ServerPlayer player : event.getPlayerList().getPlayers()) {
                FRNetwork.sendToPlayer(packet, player);
            }
        }
    }

    /**
     * Registers custom server commands.
     */
    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        StarterKitCommand.register(event.getDispatcher());
    }

    /**
     * Cancels any incoming damage if the player is currently selecting a race.
     */
    @SubscribeEvent
    public static void onLivingAttack(LivingAttackEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (PlayerConditionHandler.isSelectingRace(player)) {
                event.setCanceled(true);
            }
        }
    }

    /**
     * Prevents the player from being knocked back by attacks or explosions while selecting a race.
     */
    @SubscribeEvent
    public static void onLivingKnockBack(LivingKnockBackEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (PlayerConditionHandler.isSelectingRace(player)) {
                event.setCanceled(true);
            }
        }
    }

    /**
     * Prevents mobs from targeting the player while they are selecting a race.
     */
    @SubscribeEvent
    public static void onMobChangeTarget(LivingChangeTargetEvent event) {
        if (event.getNewTarget() instanceof Player player) {
            if (PlayerConditionHandler.isSelectingRace(player)) {
                event.setCanceled(true);
            }
        }
    }
}
