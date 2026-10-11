package org.bruskych.finesse_races.gameplay.races;

import java.util.List;
import net.minecraft.network.chat.Component;

import org.bruskych.finesse_races.gameplay.abilities.core.RaceAbility;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;

public class AvianRace extends AbstractRace {

    public AvianRace() {
        super(6, 2);
    }

    @Override
    public String getId() {
        return "avian";
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("race.finesse_races.avian.name");
    }

    @Override
    public Component getDisplayStory() {
        return Component.translatable("race.finesse_races.avian.story");
    }

    @Override
    public List<RaceAbility> getAbilities() {
        return List.of();
    }
}