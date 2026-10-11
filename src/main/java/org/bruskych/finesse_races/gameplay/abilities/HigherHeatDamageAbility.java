package org.bruskych.finesse_races.gameplay.abilities;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

import org.bruskych.finesse_races.gameplay.abilities.core.AbilityCategory;
import org.bruskych.finesse_races.gameplay.abilities.core.RaceAbility;

public class HigherHeatDamageAbility implements RaceAbility {

    // List of items to display
    public static final TagKey<Item> DISPLAY_ITEMS_TAG = ItemTags.create(
            ResourceLocation.fromNamespaceAndPath("finesse_races", "abilities/higher_heat_damage")
    );

    private final float damageMultiplier;

    public HigherHeatDamageAbility(float damageMultiplier) {
        this.damageMultiplier = damageMultiplier;
    }

    public float getDamageMultiplier() {
        return this.damageMultiplier;
    }

    @Override
    public void apply(Player player) {
        // Leave empty because damage modification is event-driven, not tick-driven
    }

    @Override
    public Component getTitle() {
        return Component.translatable("ability.finesse_races.higher_heat_damage.title");
    }

    @Override
    public Component getDescription() {
        return Component.translatable("ability.finesse_races.higher_heat_damage.desc");
    }

    @Override
    public AbilityCategory getCategory() {
        return AbilityCategory.HARMFUL;
    }

    @Override
    public TagKey<Item> getDisplayItemsTag() {
        return DISPLAY_ITEMS_TAG;
    }
}