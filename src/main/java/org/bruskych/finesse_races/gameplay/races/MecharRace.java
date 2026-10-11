package org.bruskych.finesse_races.gameplay.races;

import java.util.List;
import net.minecraft.network.chat.Component;

import org.bruskych.finesse_races.gameplay.abilities.core.RaceAbility;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;

public class MecharRace extends AbstractRace {

    public MecharRace() {
        super(5, 2);
    }

    @Override
    public String getId() {
        return "mechar";
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("race.finesse_races.mechar.name");
    }

    @Override
    public Component getDisplayStory() {
        return Component.translatable("race.finesse_races.mechar.story");
    }

    @Override
    public List<RaceAbility> getAbilities() {
        return List.of();
    }
}