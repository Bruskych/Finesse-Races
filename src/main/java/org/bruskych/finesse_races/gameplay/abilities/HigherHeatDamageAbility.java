package org.bruskych.finesse_races.gameplay.abilities;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import org.bruskych.finesse_races.gameplay.abilities.core.AbilityCategory;
import org.bruskych.finesse_races.gameplay.abilities.core.RaceAbility;
import org.bruskych.finesse_races.gameplay.abilities.modifiers.IHurtModifier;

public class HigherHeatDamageAbility implements RaceAbility, IHurtModifier {

    public static final TagKey<Item> DISPLAY_ITEMS_TAG = ItemTags.create(
            ResourceLocation.fromNamespaceAndPath("finesse_races", "abilities/higher_heat_damage")
    );

    private final float damageMultiplier;

    public HigherHeatDamageAbility(float damageMultiplier) {
        this.damageMultiplier = damageMultiplier;
    }

    @Override
    public void onHurt(Player player, LivingHurtEvent event) {
        if (event.getSource().is(DamageTypeTags.IS_FIRE)) {
            float originalDamage = event.getAmount();
            event.setAmount(originalDamage * this.damageMultiplier);
        }
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