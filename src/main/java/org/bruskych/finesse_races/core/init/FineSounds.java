package org.bruskych.finesse_races.core.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import org.bruskych.finesse_races.core.FinesseRaces;

/**
 * Handles the registration of custom sound events for the modification.
 * Utilizes Forge's DeferredRegister system to safely register sounds during the mod lifecycle.
 */
public class FineSounds {

    /**
     * The DeferredRegister for all sound events in the Finesse Races mod.
     */
    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(
                    ForgeRegistries.SOUND_EVENTS,
                    FinesseRaces.MOD_ID
            );

    // Registered Sound Events
    public static final RegistryObject<SoundEvent> ENDERIAN_WATER_DAMAGE =
            registerSound("enderian_water_damage");

    /**
     * A helper method to register a sound event with minimal boilerplate.
     * Prevents string duplication by using the provided name for both the registry and the ResourceLocation.
     *
     * @param name The internal ID name of the sound (must match the sounds.json entry).
     * @return A RegistryObject containing the created SoundEvent.
     */
    private static RegistryObject<SoundEvent> registerSound(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(FinesseRaces.MOD_ID, name);
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    /**
     * Attaches the DeferredRegister to the mod's event bus.
     * Must be called during the mod's initialization phase.
     *
     * @param eventBus The mod-specific event bus (FMLJavaModLoadingContext).
     */
    public static void register(IEventBus eventBus) {
        SOUNDS.register(eventBus);
    }
}