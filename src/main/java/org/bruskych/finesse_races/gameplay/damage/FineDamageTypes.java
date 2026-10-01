package org.bruskych.finesse_races.gameplay.damage;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

import org.bruskych.finesse_races.core.FinesseRaces;

/**
 * Creating a key (ResourceKey) to link Java code with a damage-type JSON file.
 */
public class FineDamageTypes {

    public static final ResourceKey<DamageType> WATER_DAMAGE = ResourceKey.create(
            Registries.DAMAGE_TYPE,
            ResourceLocation.fromNamespaceAndPath(
                    FinesseRaces.MOD_ID,
                    "water_damage"
            )
    );
}
