package org.bruskych.finesse_races.gameplay.races;

import java.util.List;
import net.minecraft.network.chat.Component;

import org.bruskych.finesse_races.gameplay.abilities.core.RaceAbility;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;
import org.bruskych.finesse_races.gameplay.traits.core.RaceTrait;

public class MecharRace extends AbstractRace {

    public MecharRace() {
        super(5, 2, 98, 34);
    }

    private final List<RaceAbility> abilities = List.of();

    private final List<RaceTrait> traits = List.of(
            // new StepHeightTrait(0.0D, "+0.0"),
            // new BlockReachTrait(0.0D, "+0.0"),
            // new EntityReachTrait(0.0D, "+0.0"),
            // new MovementSpeedTrait(0.0D, "+0%"),
            // new SwimSpeedTrait(0.0D, "+0%"),
            // new AttackDamageTrait(0.0D, "+0.0"),
            // new MiningSpeedTrait(0.0F, "+0%"),
            // new AttackSpeedTrait(0.0D, "+0%")
            // new EntitySizeTrait(DIMENSIONS, 0.0F, "+0.0")
    );

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
        return abilities;
    }

    @Override
    public List<RaceTrait> getTraits() {
        return traits;
    }
}