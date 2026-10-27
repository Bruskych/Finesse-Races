package org.bruskych.finesse_races.gameplay.abilities;

import net.minecraft.network.chat.Component;
import org.bruskych.finesse_races.gameplay.abilities.core.AbilityCategory;
import org.bruskych.finesse_races.gameplay.abilities.core.RaceAbility;

public class EnderKinshipAbility implements RaceAbility {

    @Override
    public Component getTitle() {
        return Component.translatable("ability.finesse_races.ender_kinship");
    }

    @Override
    public Component getDescription() {
        return Component.translatable("ability.finesse_races.ender_kinship.desc");
    }

    @Override
    public AbilityCategory getCategory() {
        return AbilityCategory.BENEFICIAL;
    }
}