package org.bruskych.finesse_races.core.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import org.bruskych.finesse_races.core.FinesseRaces;

/**
 * Registering a custom sound event via Forge's DeferredRegister.
 */
public class FineSounds {
    public static void register(IEventBus eventBus) {
        SOUNDS.register(eventBus);
    }

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(
            ForgeRegistries.SOUND_EVENTS, FinesseRaces.MOD_ID
    );

    public static final RegistryObject<SoundEvent> WATER_DAMAGE_HURT = SOUNDS.register(
            "ender_water_damage",
            () -> SoundEvent.createVariableRangeEvent(
                    ResourceLocation.fromNamespaceAndPath(FinesseRaces.MOD_ID, "ender_water_damage")
            )
    );
}
