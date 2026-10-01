package org.bruskych.finesse_races.core;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.slf4j.Logger;

import org.bruskych.finesse_races.common.network.FRNetwork;
import org.bruskych.finesse_races.client.gui.screens.RaceSelectionScreen;
import org.bruskych.remedy_core.core.other.RCClientEvents;
import org.bruskych.finesse_races.core.init.FineSounds;

@Mod(FinesseRaces.MOD_ID)
public class FinesseRaces {

    // Mod ID used for resources, registration, and networking channels
    public static final String MOD_ID = "finesse_races";

    // Logger instance for console outputs
    public static final Logger LOGGER = LogUtils.getLogger();

    public FinesseRaces() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext context = ModLoadingContext.get();

        // Register sounds
        FineSounds.register(bus);

        bus.addListener(this::commonSetup);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> bus.addListener(this::clientSetup));

        context.registerConfig(ModConfig.Type.COMMON, FRConfig.COMMON_SPEC);
        context.registerConfig(ModConfig.Type.CLIENT, FRConfig.CLIENT_SPEC);
    }

    // Common mod setup (runs on both client and server)
    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Finesse Races initialization complete.");
        event.enqueueWork(() -> {
            // Network packet logging
            FRNetwork.register();
        });
    }

    // Client-side setup and initialization events
    private void clientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("The Finesse Races client-side component has been initialized.");

        event.enqueueWork(() -> {
            RCClientEvents.addLoginAction(() -> {
                Minecraft mc = Minecraft.getInstance();
                mc.setScreen(new RaceSelectionScreen());
            });
        });
    }
}