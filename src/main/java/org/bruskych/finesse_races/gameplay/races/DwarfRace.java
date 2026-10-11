package org.bruskych.finesse_races.gameplay.races;

import java.util.List;
import net.minecraft.network.chat.Component;

import org.bruskych.finesse_races.gameplay.abilities.core.RaceAbility;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;

public class DwarfRace extends AbstractRace {

    public DwarfRace() {
        super(1, 2);
    }

    @Override
    public String getId() {
        return "dwarf";
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("race.finesse_races.dwarf.name");
    }

    @Override
    public Component getDisplayStory() {
        return Component.translatable("race.finesse_races.dwarf.story");
    }

    @Override
    public List<RaceAbility> getAbilities() {
        return List.of();
    }
}