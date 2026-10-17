package org.bruskych.finesse_races.gameplay.races;

import java.util.List;
import net.minecraft.network.chat.Component;

import org.bruskych.finesse_races.gameplay.abilities.core.RaceAbility;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;

public class AlienRace extends AbstractRace {

    public AlienRace() {
        super(11, 2, 80, 17);
    }

    @Override
    public String getId() {
        return "alien";
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("race.finesse_races.alien.name");
    }

    @Override
    public Component getDisplayStory() {
        return Component.translatable("race.finesse_races.alien.story");
    }

    @Override
    public List<RaceAbility> getAbilities() {
        return List.of();
    }
}