package org.bruskych.finesse_races.gameplay.races;

import java.util.List;
import net.minecraft.network.chat.Component;

import org.bruskych.finesse_races.gameplay.abilities.WaterDamageAbility;
import org.bruskych.finesse_races.gameplay.abilities.core.RaceAbility;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;

public class EnderianRace extends AbstractRace {

    private final List<RaceAbility> abilities = List.of(
            new WaterDamageAbility(1.0F, 1.0F)
    );

    public EnderianRace() {
        super(4, 2);
    }

    @Override
    public String getId() {
        return "enderian";
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("race.finesse_races.enderian.name");
    }

    @Override
    public Component getDisplayStory() {
        return Component.translatable("race.finesse_races.enderian.story");
    }

    @Override
    public List<RaceAbility> getAbilities() {
        return abilities;
    }
}