package org.bruskych.finesse_races.gameplay.races;

import java.util.List;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Pose;

import org.bruskych.finesse_races.gameplay.abilities.core.RaceAbility;
import org.bruskych.finesse_races.gameplay.races.appearance.PlayerAppearanceProfile;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;
import org.bruskych.finesse_races.gameplay.races.core.PlayerDimensionsProfile;
import org.bruskych.finesse_races.gameplay.traits.core.RaceTrait;

public class VampireRace extends AbstractRace {

    private static final PlayerDimensionsProfile DIMENSIONS = PlayerDimensionsProfile.builder()
            .addToVanilla(Pose.STANDING, 0.0F, 0.125F)
            .addToVanilla(Pose.CROUCHING, 0.0F, 0.125F)
            .eyeHeightOffset(Pose.STANDING, 0.0625F)
            .eyeHeightOffset(Pose.CROUCHING, 0.0625F)
            .build();

    private static final PlayerAppearanceProfile APPEARANCE = PlayerAppearanceProfile.builder()
            .head(2.0F, 0.0F, 0.0F).replaceHead(true)
            .build();

    @Override
    public PlayerAppearanceProfile getAppearanceProfile() {
        return APPEARANCE;
    }

    public VampireRace() {
        super(3, 2, 44, 34);
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
        return abilities;
    }

    @Override
    public List<RaceTrait> getTraits() {
        return traits;
    }
}