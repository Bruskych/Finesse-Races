package org.bruskych.finesse_races.gameplay.abilities;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import org.bruskych.finesse_races.gameplay.abilities.core.AbilityCategory;
import org.bruskych.finesse_races.gameplay.abilities.core.RaceAbility;
import org.bruskych.finesse_races.gameplay.abilities.modifiers.IHurtModifier;

public class HeatResistanceAbility implements RaceAbility, IHurtModifier {

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
    public void onHurt(Player player, LivingHurtEvent event) {
        if (event.getSource().is(DamageTypes.HOT_FLOOR)) {
            if (this.immuneToMagma) {
                event.setCanceled(true);
            }
        } else if (event.getSource().is(DamageTypeTags.IS_FIRE)) {
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