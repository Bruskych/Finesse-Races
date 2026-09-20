package org.bruskych.finesse_races.core;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.bruskych.finesse_races.client.gui.screens.RaceSelectionScreen;
import org.bruskych.remedy_core.core.other.RCClientEvents;
import org.slf4j.Logger;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

@Mod(FinesseRaces.MOD_ID)
public class FinesseRaces {

    // Идентификатор мода (используется для ресурсов, регистрации и каналов)
    public static final String MOD_ID = "finesse_races";

    // Логгер для вывода сообщений в консоль
    public static final Logger LOGGER = LogUtils.getLogger();

    public FinesseRaces() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext context = ModLoadingContext.get();

        MinecraftForge.EVENT_BUS.register(this);

        bus.addListener(this::commonSetup);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> bus.addListener(this::clientSetup));

        context.registerConfig(ModConfig.Type.COMMON, FRConfig.COMMON_SPEC);
        context.registerConfig(ModConfig.Type.CLIENT, FRConfig.CLIENT_SPEC);
    }

    // Общая инициализация мода (выполняется на клиенте и сервере)
    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Finesse Races initialization complete.");
    }

    // Клиентские события загрузки
    private void clientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("The Finesse Races client-side component has been initialized.");

        event.enqueueWork(() -> {
            RCClientEvents.addLoginAction(() -> {
                Minecraft mc = Minecraft.getInstance();
                if (FRConfig.CLIENT.enableRaceSelectionOnFirstJoin.get()) {
                    mc.setScreen(new RaceSelectionScreen());
                }
            });
        });
    }
}