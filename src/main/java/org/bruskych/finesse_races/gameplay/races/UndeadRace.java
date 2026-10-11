package org.bruskych.finesse_races.gameplay.races;

import java.util.List;
import net.minecraft.network.chat.Component;

import org.bruskych.finesse_races.gameplay.abilities.core.RaceAbility;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;

public class UndeadRace extends AbstractRace {

    public UndeadRace() {
        super(10, 2); }

    @Override
    public String getId() {
        return "undead";
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("race.finesse_races.undead.name");
    }

    @Override
    public Component getDisplayStory() {
        return Component.translatable("race.finesse_races.undead.story");
    }

    @Override
    public List<RaceAbility> getAbilities() {
        return List.of();
    }
}