package org.bruskych.finesse_races.gameplay.abilities;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import org.bruskych.finesse_races.gameplay.abilities.core.AbilityCategory;
import org.bruskych.finesse_races.gameplay.abilities.core.RaceAbility;
import org.bruskych.finesse_races.gameplay.abilities.modifiers.IExtendedReachModifier;

/**
 * Increases the player's interaction range for blocks and entities.
 * These values are safely applied as Forge Attribute Modifiers to avoid conflicts.
 */
public class LongReachAbility implements RaceAbility, IExtendedReachModifier {

    public static final TagKey<Item> DISPLAY_ITEMS_TAG = ItemTags.create(
            ResourceLocation.fromNamespaceAndPath("finesse_races", "abilities/long_reach")
    );

    private final double blockReachBonus;
    private final double entityReachBonus;

    /**
     * @param blockReachBonus  Additional reach distance in blocks for placing/mining.
     * @param entityReachBonus Additional reach distance in blocks for attacking/interacting with entities.
     */
    public LongReachAbility(double blockReachBonus, double entityReachBonus) {
        this.blockReachBonus = blockReachBonus;
        this.entityReachBonus = entityReachBonus;
    }

    @Override
    public double getBlockReachBonus() {
        return this.blockReachBonus;
    }

    @Override
    public double getEntityReachBonus() {
        return this.entityReachBonus;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("ability.finesse_races.long_reach.title");
    }

    @Override
    public Component getDescription() {
        return Component.translatable("ability.finesse_races.long_reach.desc");
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
