package org.bruskych.finesse_races.gameplay.races;

import java.util.List;
import net.minecraft.network.chat.Component;

import org.bruskych.finesse_races.gameplay.abilities.core.RaceAbility;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;

public class VampireRace extends AbstractRace {

    public VampireRace() {
        super(3, 2, 44, 17);
    }

    @Override
    public String getId() {
        return "vampire";
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("race.finesse_races.vampire.name");
    }

    @Override
    public Component getDisplayStory() {
        return Component.translatable("race.finesse_races.vampire.story");
    }

    @Override
    public List<RaceAbility> getAbilities() {
        return List.of();
    }
}