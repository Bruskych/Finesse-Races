package org.bruskych.finesse_races.gameplay.abilities;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;

import org.bruskych.finesse_races.gameplay.abilities.core.AbilityCategory;
import org.bruskych.finesse_races.gameplay.abilities.core.RaceAbility;
import org.bruskych.finesse_races.gameplay.abilities.modifiers.IItemUseModifier;
import org.bruskych.finesse_races.gameplay.abilities.modifiers.IPotionSplashModifier;
import org.bruskych.finesse_races.gameplay.abilities.modifiers.ITickModifier;
import org.bruskych.finesse_races.gameplay.conditions.PlayerConditionHandler;
import org.bruskych.finesse_races.gameplay.damage.FineDamageSources;
import org.bruskych.finesse_races.gameplay.damage.FineDamageTypes;

/**
 * A harmful ability that damages the player when they interact with water.
 * This includes being submerged, standing in rain, drinking potions, or being hit by splash potions.
 */
public class WaterDamageAbility implements RaceAbility, IItemUseModifier, IPotionSplashModifier, ITickModifier {

    public static final TagKey<Item> DISPLAY_ITEMS_TAG = ItemTags.create(
            ResourceLocation.fromNamespaceAndPath("finesse_races", "abilities/enderian_water_damage")
    );

    private final float partialDamage;
    private final float fullDamage;

    /**
     * @param partialDamage Damage taken per interval from light water exposure (e.g., rain, shallow water).
     * @param fullDamage    Damage taken per interval from heavy exposure (e.g., full submersion, drinking).
     */
    public WaterDamageAbility(float partialDamage, float fullDamage) {
        this.partialDamage = partialDamage;
        this.fullDamage = fullDamage;
    }

    @Override
    public void onTick(Player player) {
        if (PlayerConditionHandler.isDrown(player)) return;

        DamageSource waterSource = FineDamageSources
                .create(player.level(), FineDamageTypes.ENDERIAN_WATER_DAMAGE);

        if (PlayerConditionHandler.isUnderWater(player)) {
            // Check every 15 ticks (0.75 seconds)
            if (player.tickCount % 15 == 0) player.hurt(waterSource, fullDamage);
        } else if (PlayerConditionHandler.isWater(player)) {
            // Check every 20 ticks (1 second)
            if (player.tickCount % 20 == 0) player.hurt(waterSource, partialDamage);
        } else if (PlayerConditionHandler.isRain(player)) {
            // Check every 30 ticks (1.5 second)
            if (player.tickCount % 30 == 0) player.hurt(waterSource, partialDamage);
        }
    }

    @Override
    public void onItemUseFinish(Player player, ItemStack item, UseAnim anim) {
        // If the usage animation is drinking, deal damage to the player.
        if (anim == UseAnim.DRINK) {
            player.hurt(
                    FineDamageSources.create(player.level(), FineDamageTypes.ENDERIAN_WATER_DAMAGE),
                    fullDamage
            );
        }
    }

    @Override
    public void onSplashPotionHit(Player player, ThrownPotion potion) {
        // If a potion shattered near the player
        player.hurt(
                FineDamageSources.create(player.level(), FineDamageTypes.ENDERIAN_WATER_DAMAGE),
                fullDamage
        );
    }

    @Override
    public Component getTitle() {
        return Component.translatable("ability.finesse_races.enderian_water_damage.title");
    }

    @Override
    public Component getDescription() {
        return Component.translatable("ability.finesse_races.enderian_water_damage.desc");
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