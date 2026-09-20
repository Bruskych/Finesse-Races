package org.bruskych.finesse_races.core.other;

import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.bruskych.finesse_races.core.FinesseRaces;
import static org.bruskych.finesse_races.core.FinesseRaces.LOGGER;

@Mod.EventBusSubscriber(modid = FinesseRaces.MOD_ID)
public class FREvents {

    // Событие запуска сервера
    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("The server starts up alongside Finesse Races.");
    }
}
