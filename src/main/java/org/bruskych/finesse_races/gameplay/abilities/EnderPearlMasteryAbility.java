package org.bruskych.finesse_races.gameplay.abilities;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import org.bruskych.finesse_races.gameplay.abilities.core.AbilityCategory;
import org.bruskych.finesse_races.gameplay.abilities.core.RaceAbility;
import org.bruskych.finesse_races.gameplay.abilities.modifiers.IEnderPearlModifier;

/**
 * Ability that improves ender pearl throwing distance and removes teleport damage.
 */
public class EnderPearlMasteryAbility implements RaceAbility, IEnderPearlModifier {

    public static final TagKey<Item> DISPLAY_ITEMS_TAG = ItemTags.create(
            ResourceLocation.fromNamespaceAndPath("finesse_races", "abilities/ender_pearl_mastery")
    );

    private final float velocityMultiplier;
    private final boolean preventDamage;
    private final boolean evaporatesInFluid;

    /**
     * @param velocityMultiplier Throw power multiplier (1.0F is vanilla, 1.5F is 50% further).
     * @param preventDamage      True to cancel teleportation damage.
     * @param evaporatesInFluid  True to destroy the pearl upon touching water/lava/modded fluids.
     */
    public EnderPearlMasteryAbility(float velocityMultiplier, boolean preventDamage, boolean evaporatesInFluid) {
        this.velocityMultiplier = velocityMultiplier;
        this.preventDamage = preventDamage;
        this.evaporatesInFluid = evaporatesInFluid;
    }

    @Override
    public float getPearlThrowVelocityMultiplier() {
        return this.velocityMultiplier;
    }

    @Override
    public boolean preventsPearlDamage() {
        return this.preventDamage;
    }

    @Override
    public boolean evaporatesInFluids() {
        return this.evaporatesInFluid;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("ability.finesse_races.ender_pearl_mastery.title");
    }

    @Override
    public Component getDescription() {
        return Component.translatable("ability.finesse_races.ender_pearl_mastery.desc");
    }

    @Override
    public AbilityCategory getCategory() {
        return AbilityCategory.NEUTRAL;
    }

    @Override
    public TagKey<Item> getDisplayItemsTag() {
        return DISPLAY_ITEMS_TAG;
    }
}
