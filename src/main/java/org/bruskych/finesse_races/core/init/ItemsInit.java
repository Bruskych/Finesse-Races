package org.bruskych.finesse_races.core.init;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.bruskych.finesse_races.core.FinesseRaces;
import org.bruskych.finesse_races.common.items.RaceOrbItem;

/**
 * Items Registry.
 * Handles item creation and registration with Forge event bus.
 */
public class ItemsInit {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, FinesseRaces.MOD_ID);

    public static final RegistryObject<Item> RACE_ORB = ITEMS.register("race_orb",
            () -> new RaceOrbItem(new Item.Properties()
                    .stacksTo(1)
                    .fireResistant()
            ));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}