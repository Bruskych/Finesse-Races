package org.bruskych.finesse_races.gameplay.damage;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;

/**
 * A factory for generating a DamageSource object.
 */
public class FineDamageSources {

    public static DamageSource waterDamage(Player player) {
        return new DamageSource(player
                .level()
                .registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(FineDamageTypes.WATER_DAMAGE)
        );
    }
}