package org.bruskych.finesse_races.gameplay.abilities;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import org.bruskych.finesse_races.gameplay.abilities.core.AbilityCategory;
import org.bruskych.finesse_races.gameplay.abilities.core.RaceAbility;

public class HigherHeatDamageAbility implements RaceAbility {

    private final float damageMultiplier;

    public HigherHeatDamageAbility(float damageMultiplier) {
        this.damageMultiplier = damageMultiplier;
    }

    public float getDamageMultiplier() {
        return this.damageMultiplier;
    }

    // Leave empty because damage modification is event-driven, not tick-driven
    @Override
    public void apply(Player player) {
        // TODO: Empty?
    }

    @Override
    public Component getTitle() {
        return Component.translatable("ability.finesse_races.higher_heat_damage.title");
    }

    @Override
    public Component getDescription() {
        return Component.translatable("ability.finesse_races.higher_heat_damage.desc");
    }

    @Override
    public AbilityCategory getCategory() {
        return AbilityCategory.DEBUFF;
    }
}