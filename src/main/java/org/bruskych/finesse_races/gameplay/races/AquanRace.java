package org.bruskych.finesse_races.gameplay.races;

import java.util.List;
import net.minecraft.network.chat.Component;

import org.bruskych.finesse_races.gameplay.abilities.RaceAbility;
import org.bruskych.finesse_races.gameplay.abilities.WaterDamageAbility;

public class AquanRace extends AbstractRace {

    private final List<RaceAbility> abilities = List.of(
            new WaterDamageAbility(1.0F, 1.0F)
            // TODO: No continue list (optimization)
    );

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

    // Returns the list of abilities to the base class to activate them
    @Override
    public List<RaceAbility> getAbilities() {
        return abilities;
    }
}
