package org.bruskych.finesse_races.core.other;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import org.bruskych.finesse_races.core.FinesseRaces;
import org.bruskych.finesse_races.gameplay.races.AbstractRace;
import org.bruskych.finesse_races.gameplay.races.RaceManager;
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

        // Applying config settings to all registered races.
        RaceManager.applyConfigSettings();
    }

    // Triggered once when a player logs into the server
    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        RaceManager.loadPlayerRace(event.getEntity());
    }

    // Triggered once when a player logs out of the server
    @SubscribeEvent
    public static void onPlayerLeave(PlayerEvent.PlayerLoggedOutEvent event) {
        RaceManager.unloadPlayerRace(event.getEntity());
    }

    // Checks player state. Called every tick (20 times per second).
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
}
