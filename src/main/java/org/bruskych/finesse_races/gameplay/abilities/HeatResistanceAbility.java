package org.bruskych.finesse_races.gameplay.abilities;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import org.bruskych.finesse_races.gameplay.abilities.core.AbilityCategory;
import org.bruskych.finesse_races.gameplay.abilities.core.RaceAbility;
import org.bruskych.finesse_races.gameplay.abilities.modifiers.IAttackModifier;
import org.bruskych.finesse_races.gameplay.abilities.modifiers.IHurtModifier;
import org.bruskych.finesse_races.gameplay.abilities.modifiers.ILavaVisionModifier;

public class HeatResistanceAbility implements RaceAbility, IHurtModifier, IAttackModifier, ILavaVisionModifier {

    public static final TagKey<Item> DISPLAY_ITEMS_TAG = ItemTags.create(
            ResourceLocation.fromNamespaceAndPath("finesse_races", "abilities/heat_resistance")
    );

    private final float fireDamageMultiplier;
    private final boolean immuneToMagma;
    private final boolean betterLavaVisibility;

    /**
     * @param fireDamageMultiplier Fire/lava damage multiplier (e.g., 0.5f means taking 50% of the damage).
     * @param immuneToMagma        Complete immunity to magma blocks.
     * @param betterLavaVisibility Improved visibility underwater.
     */
    public HeatResistanceAbility(float fireDamageMultiplier, boolean immuneToMagma, boolean betterLavaVisibility) {
        this.fireDamageMultiplier = fireDamageMultiplier;
        this.immuneToMagma = immuneToMagma;
        this.betterLavaVisibility = betterLavaVisibility;
    }

    public boolean hasBetterLavaVisibility() {
        return this.betterLavaVisibility;
    }

    @Override
    public void onAttack(Player player, LivingAttackEvent event) {
        // Intercepts the attack before sounds or animations play.
        // If the damage source is magma and the player is immune, cancel the attack entirely.
        if (event.getSource().is(DamageTypes.HOT_FLOOR) && this.immuneToMagma) {
            event.setCanceled(true);
        }
    }

    @Override
    public void onHurt(Player player, LivingHurtEvent event) {
        // Modifies the final damage amount if the attack was not canceled.
        // Multiplies the damage by the race's specific fire resistance factor.
        if (event.getSource().is(DamageTypeTags.IS_FIRE)) {
            float originalDamage = event.getAmount();
            event.setAmount(originalDamage * this.fireDamageMultiplier);
        }
    }

    @Override
    public Component getTitle() {
        return Component.translatable("ability.finesse_races.heat_resistance.title");
    }

    @Override
    public Component getDescription() {
        return Component.translatable("ability.finesse_races.heat_resistance.desc");
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