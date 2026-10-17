package org.bruskych.finesse_races.gameplay.races;

import java.util.List;
import net.minecraft.network.chat.Component;
import org.bruskych.finesse_races.gameplay.abilities.core.RaceAbility;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;

public class WerewolfRace extends AbstractRace {

    public WerewolfRace() {
        super(7, 2, 125, 17);
    }

    @Override
    public String getId() {
        return "werewolf";
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("race.finesse_races.werewolf.name");
    }

    @Override
    public Component getDisplayStory() {
        return Component.translatable("race.finesse_races.werewolf.story");
    }

    @Override
    public List<RaceAbility> getAbilities() {
        return List.of();
    }
}