package org.bruskych.finesse_races.gameplay.abilities;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.bruskych.finesse_races.core.FinesseRaces;
import org.bruskych.finesse_races.gameplay.abilities.core.AbilityCategory;
import org.bruskych.finesse_races.gameplay.abilities.core.RaceAbility;

public class EnderKinshipAbility implements RaceAbility {

    public static final TagKey<Item> DISPLAY_ITEMS_TAG = ItemTags.create(
            ResourceLocation.fromNamespaceAndPath(FinesseRaces.MOD_ID, "abilities/ender_kinship_items")
    );

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

    @Override
    public TagKey<Item> getDisplayItemsTag() {
        return DISPLAY_ITEMS_TAG;
    }
}