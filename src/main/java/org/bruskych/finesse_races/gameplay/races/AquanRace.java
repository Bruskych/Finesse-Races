package org.bruskych.finesse_races.gameplay.races;

import java.util.List;
import net.minecraft.network.chat.Component;

import org.bruskych.finesse_races.gameplay.abilities.HigherHeatDamageAbility;
import org.bruskych.finesse_races.gameplay.abilities.core.RaceAbility;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;
import org.bruskych.finesse_races.gameplay.traits.core.RaceTrait;

/**
 * Aquan race implementation.
 */
public class AquanRace extends AbstractRace {

    public AquanRace() {
        super(2, 2, 35, 34);
    }

    private final List<RaceAbility> abilities = List.of(
            new HigherHeatDamageAbility(2.0F)
    );

    private final List<RaceTrait> traits = List.of(
            // new StepHeightTrait(0.0D, "+0.0"),
            // new BlockReachTrait(0.0D, "+0.0"),
            // new EntityReachTrait(0.0D, "+0.0"),
            // new MovementSpeedTrait(0.0D, "+0%"),
            // new SwimSpeedTrait(0.0D, "+0%"),
            // new AttackDamageTrait(0.0D, "+0.0"),
            // new MiningSpeedTrait(0.0F, "+0%"),
            // new AttackSpeedTrait(0.0D, "+0%")
    );

    @Override
    public String getId() {
        return "aquan";
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("race.finesse_races.aquan.name");
    }

    @Override
    public Component getDisplayStory() {
        return Component.translatable("race.finesse_races.aquan.story");
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
