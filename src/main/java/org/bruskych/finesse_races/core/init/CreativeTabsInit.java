package org.bruskych.finesse_races.core.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.bruskych.finesse_races.core.FinesseRaces;

/**
 * Handles creation and registration of custom mod inventory tabs.
 */
public class CreativeTabsInit {

    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FinesseRaces.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MAIN_TAB = TABS.register("main",
            () -> CreativeModeTab.builder()

                    .title(Component.translatable("creativetab.finesse_races.main"))

                    .icon(() -> ItemsInit.RACE_ORB.get().getDefaultInstance())

                    .displayItems((parameters, output) -> {
                        output.accept(ItemsInit.RACE_ORB.get());
                    })

                    .build());

    public static void register(IEventBus eventBus) {
        TABS.register(eventBus);
    }
}