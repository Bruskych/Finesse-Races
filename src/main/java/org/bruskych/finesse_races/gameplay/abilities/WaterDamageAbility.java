package org.bruskych.finesse_races.gameplay.abilities;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import org.bruskych.finesse_races.gameplay.abilities.core.AbilityCategory;
import org.bruskych.finesse_races.gameplay.abilities.core.RaceAbility;
import org.bruskych.finesse_races.gameplay.conditions.PlayerConditionHandler;
import org.bruskych.finesse_races.gameplay.damage.FineDamageSources;

public class WaterDamageAbility implements RaceAbility {

    private final float partialDamage;
    private final float fullDamage;

    public WaterDamageAbility(float partialDamage, float fullDamage) {
        this.partialDamage = partialDamage;
        this.fullDamage = fullDamage;
    }

    @Override
    public void apply(Player player) {
        if (PlayerConditionHandler.isDrown(player)) {
            return;
        }
        // Check every 15 ticks (0.75 seconds)
        if (PlayerConditionHandler.isUnderWater(player)) {
            if (player.tickCount % 15 == 0) {
                player.hurt(FineDamageSources.waterDamage(player), fullDamage);
            }
        }
        // Check every 20 ticks (1 second)
        else if (PlayerConditionHandler.isWater(player)) {
            if (player.tickCount % 20 == 0) {
                player.hurt(FineDamageSources.waterDamage(player), partialDamage);
            }
        }
        // Check every 30 ticks (1.5 second)
        else if (PlayerConditionHandler.isRain(player)) {
            if (player.tickCount % 30 == 0) {
                player.hurt(FineDamageSources.waterDamage(player), partialDamage);
            }
        }
    }

    @Override
    public Component getTitle() {
        return Component.translatable("ability.finesse_races.water_damage.title");
    }

    @Override
    public Component getDescription() {
        return Component.translatable("ability.finesse_races.water_damage.desc");
    }

    @Override
    public AbilityCategory getCategory() {
        return AbilityCategory.HARMFUL;
    }
}
