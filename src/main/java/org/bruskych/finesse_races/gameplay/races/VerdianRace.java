package org.bruskych.finesse_races.gameplay.races;

import java.util.List;
import net.minecraft.network.chat.Component;

import org.bruskych.finesse_races.gameplay.abilities.core.RaceAbility;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;

public class VerdianRace extends AbstractRace {

    public VerdianRace() {
        super(8, 2);
    }

    @Override
    public String getId() {
        return "verdian";
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("race.finesse_races.verdian.name");
    }

    @Override
    public Component getDisplayStory() {
        return Component.translatable("race.finesse_races.verdian.story");
    }

    @Override
    public List<RaceAbility> getAbilities() {
        return List.of();
    }
}