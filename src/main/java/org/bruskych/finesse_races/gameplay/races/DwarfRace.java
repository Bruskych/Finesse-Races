package org.bruskych.finesse_races.gameplay.races;

import java.util.List;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Pose;

import org.bruskych.finesse_races.gameplay.abilities.core.RaceAbility;
import org.bruskych.finesse_races.gameplay.races.appearance.ArmorFitProfile;
import org.bruskych.finesse_races.gameplay.races.appearance.PlayerAppearanceProfile;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;
import org.bruskych.finesse_races.gameplay.races.core.PlayerDimensionsProfile;
import org.bruskych.finesse_races.gameplay.traits.EntitySizeTrait;
import org.bruskych.finesse_races.gameplay.traits.core.RaceTrait;

public class DwarfRace extends AbstractRace {

    private static final PlayerDimensionsProfile DIMENSIONS = PlayerDimensionsProfile.builder()
            .addToVanilla(Pose.STANDING, 0.0F, -0.5F)
            .addToVanilla(Pose.CROUCHING, 0.0F, -0.5F)
            .addToVanilla(Pose.SLEEPING, 0.05F, -0.05F)
            .eyeHeightOffset(Pose.STANDING, -0.5F)
            .eyeHeightOffset(Pose.CROUCHING, -0.5F)
            .build();

    private static final PlayerAppearanceProfile APPEARANCE = PlayerAppearanceProfile.builder()
            .arms(-2.0F, 0.0F, 0.0F).replaceArms(true)
            .legs(-4.0F, 0.0F, 0.0F).replaceLegs(true)
            .body(-4.0F, 0.0F, 0.0F).replaceBody(true)
            .build();

    @Override
    public PlayerAppearanceProfile getAppearanceProfile() {
        return APPEARANCE;
    }

    public DwarfRace() {
        super(1, 2, 89, 34);
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

            // Only for UI
            new EntitySizeTrait(DIMENSIONS, -0.5F, "-0.5")
    );

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
        return abilities;
    }

    @Override
    public List<RaceTrait> getTraits() {
        return traits;
    }
}