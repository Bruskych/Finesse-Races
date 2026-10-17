package org.bruskych.finesse_races.gameplay.races;

import java.util.List;
import net.minecraft.network.chat.Component;

import org.bruskych.finesse_races.gameplay.abilities.HigherHeatDamageAbility;
import org.bruskych.finesse_races.gameplay.abilities.core.RaceAbility;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;

/**
 * Aquan race implementation.
 */
public class AquanRace extends AbstractRace {

    private final List<RaceAbility> abilities = List.of(
            new HigherHeatDamageAbility(2.0F)
    );

    public AquanRace() {
        super(2, 2, 35, 17);
    }

    @Override
    public String getId() {
        return "aquan";
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("race.finesse_races.aquan.name");
    }

    @Override
    public Component getDisplayStory() {
        return Component.translatable("race.finesse_races.aquan.story");
    }

    @Override
    public List<RaceAbility> getAbilities() {
        return abilities;
    }
}
