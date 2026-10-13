package org.bruskych.finesse_races.gameplay.abilities.core;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;

import java.util.ArrayList;
import java.util.List;

/**
 * Ability interface.
 * A unified contract for all mod mechanics.
 * Ensures that any created ability has an apply method.
 */
public interface RaceAbility {

    /**
     * Applies the ability logic to the player. Usually called every tick.
     */
    void apply(Player player);

    /**
     * Called when the player finishes using an item (ate, drank, etc.).
     */
    default void onItemUseFinish(Player player, ItemStack item, UseAnim anim) {
        // Empty
    }

    /**
     * Returns the localized title of the ability for the menu.
     */
    Component getTitle();

    /**
     * Returns the localized description of the ability for the menu.
     */
    Component getDescription();

    /**
     * Returns the category of the ability (Beneficial, Harmful, or Neutral).
     */
    AbilityCategory getCategory();

    /**
     * Specifies which item tag (from JSON) belongs to this ability. Returns null if none.
     */
    default TagKey<Item> getDisplayItemsTag() {
        return null;
    }

    /**
     * Automatically converts the specified item tag into a list of real items to display in the menu.
     */
    default List<ItemStack> getDisplayItems() {
        TagKey<Item> tag = getDisplayItemsTag();
        if (tag == null) return List.of();

        List<ItemStack> items = new ArrayList<>();
        var tagElements = BuiltInRegistries.ITEM.getTag(tag);

        if (tagElements.isPresent()) {
            for (Holder<Item> itemHolder : tagElements.get()) {
                Item item = itemHolder.value();
                if (item != Items.AIR) {
                    items.add(new ItemStack(item));
                }
            }
        }
        return items;
    }
}