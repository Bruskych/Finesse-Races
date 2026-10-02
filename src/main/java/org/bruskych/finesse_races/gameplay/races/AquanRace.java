package org.bruskych.finesse_races.gameplay.races;

import java.util.List;
import net.minecraft.network.chat.Component;

import org.bruskych.finesse_races.gameplay.abilities.HigherHeatDamageAbility;
import org.bruskych.finesse_races.gameplay.abilities.core.RaceAbility;
import org.bruskych.finesse_races.gameplay.abilities.WaterDamageAbility;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;

/**
 * Aquan race implementation.
 */
public class AquanRace extends AbstractRace {

    private final List<RaceAbility> abilities = List.of(
            new WaterDamageAbility(1.0F, 1.0F),
            new HigherHeatDamageAbility(2.0F)
    );

    //Returns the technical identifier for the race.
    @Override
    public String getId() {
        return "aquan";
    }

    // Returns the localized display name for the RaceSelectionScreen.
    @Override
    public Component getDisplayName() {
        return Component.translatable("race.finesse_races.aquan.name");
    }

    // Returns the localized story/lore for the RaceSelectionScreen.
    @Override
    public Component getDisplayStory() {
        return Component.translatable("race.finesse_races.aquan.story");
    }

    // Returns the list of abilities to the base class to activate them
    @Override
    public List<RaceAbility> getAbilities() {
        return abilities;
    }
}
