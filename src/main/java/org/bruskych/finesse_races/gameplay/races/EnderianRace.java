package org.bruskych.finesse_races.gameplay.races;

import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Pose;

import org.bruskych.finesse_races.gameplay.races.core.PlayerDimensionsProfile;
import org.bruskych.finesse_races.gameplay.abilities.EnderKinshipAbility;
import org.bruskych.finesse_races.gameplay.abilities.HeatResistanceAbility;
import org.bruskych.finesse_races.gameplay.abilities.WaterDamageAbility;
import org.bruskych.finesse_races.gameplay.abilities.core.RaceAbility;
import org.bruskych.finesse_races.gameplay.abilities.EnderPearlMasteryAbility;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;
import org.bruskych.finesse_races.gameplay.traits.*;
import org.bruskych.finesse_races.gameplay.traits.core.RaceTrait;

public class EnderianRace extends AbstractRace {

    private static final PlayerDimensionsProfile DIMENSIONS = PlayerDimensionsProfile
            .builder()
            .addToVanilla(Pose.STANDING, -0.1F, 0.5F)
            .addToVanilla(Pose.CROUCHING, -0.1F, 0.5F)
            .addToVanilla(Pose.SLEEPING, 0.05F, 0.05F)
            .eyeHeightOffset(Pose.STANDING, 0.5F)
            .eyeHeightOffset(Pose.CROUCHING, 0.5F)
            .build();

    public EnderianRace() {
        super(4, 2, 53, 34);
    }

    private final List<RaceAbility> abilities = List.of(
            new EnderKinshipAbility(),
            new WaterDamageAbility(1.0F, 2.0F),
            new HeatResistanceAbility(0.5F, true, true),
            new EnderPearlMasteryAbility(1.5F, true, true)
    );

    private final List<RaceTrait> traits = List.of(
            new StepHeightTrait(0.35D, "+0.35"),
            new BlockReachTrait(1.5D, "+1.5"),
            new EntityReachTrait(1.0D, "+1.0"),
            new MovementSpeedTrait(0.1D, "+10%"),
            new SwimSpeedTrait(-0.2D, "-20%"),
            new AttackDamageTrait(1.0D, "+1.0"),
            new MiningSpeedTrait(0.2F, "+20%"),
            new AttackSpeedTrait(0.15D, "+15%"),

            // Only for UI
            new EntitySizeTrait(DIMENSIONS, 0.5F, "+0.5")
    );

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

    @Override
    public List<RaceTrait> getTraits() {
        return traits;
    }
}