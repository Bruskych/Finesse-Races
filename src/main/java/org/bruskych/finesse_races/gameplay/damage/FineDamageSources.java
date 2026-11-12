package org.bruskych.finesse_races.gameplay.damage;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

/**
 * A universal factory for creating {@link DamageSource} objects.
 */
public class FineDamageSources {

    /**
     * Creates a basic damage source (without a specific attacker, e.g., environmental damage).
     *
     * @param level      The level where the event occurs (required to access the registry).
     * @param damageType The resource key of the damage type (from FineDamageTypes).
     * @return The generated DamageSource.
     */
    public static DamageSource create(Level level, ResourceKey<DamageType> damageType) {
        return new DamageSource(
                level.registryAccess()
                        .registryOrThrow(Registries.DAMAGE_TYPE)
                        .getHolderOrThrow(damageType)
        );
    }

    /**
     * Creates a damage source with a specific attacking entity (e.g., a player or a mob dealing damage).
     *
     * @param level      The level where the event occurs.
     * @param damageType The resource key of the damage type.
     * @param attacker   The entity dealing the damage.
     * @return The generated DamageSource.
     */
    public static DamageSource createEntityDamage(Level level, ResourceKey<DamageType> damageType, Entity attacker) {
        return new DamageSource(
                level.registryAccess()
                        .registryOrThrow(Registries.DAMAGE_TYPE)
                        .getHolderOrThrow(damageType), attacker
        );
    }
}