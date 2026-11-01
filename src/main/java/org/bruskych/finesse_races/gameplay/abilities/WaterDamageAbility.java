package org.bruskych.finesse_races.gameplay.abilities;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
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

public class WaterDamageAbility implements RaceAbility, IItemUseModifier, IPotionSplashModifier, ITickModifier {

    public static final TagKey<Item> DISPLAY_ITEMS_TAG = ItemTags.create(
            ResourceLocation.fromNamespaceAndPath("finesse_races", "abilities/water_damage")
    );

    private final float partialDamage;
    private final float fullDamage;

    public WaterDamageAbility(float partialDamage, float fullDamage) {
        this.partialDamage = partialDamage;
        this.fullDamage = fullDamage;
    }

    @Override
    public void onTick(Player player) {
        if (PlayerConditionHandler.isDrown(player)) return;

        if (PlayerConditionHandler.isUnderWater(player)) {
            // Check every 15 ticks (0.75 seconds)
            if (player.tickCount % 15 == 0) player.hurt(FineDamageSources.waterDamage(player), fullDamage);
        } else if (PlayerConditionHandler.isWater(player)) {
            // Check every 20 ticks (1 second)
            if (player.tickCount % 20 == 0) player.hurt(FineDamageSources.waterDamage(player), partialDamage);
        } else if (PlayerConditionHandler.isRain(player)) {
            // Check every 30 ticks (1.5 second)
            if (player.tickCount % 30 == 0) player.hurt(FineDamageSources.waterDamage(player), partialDamage);
        }
    }

    @Override
    public void onItemUseFinish(Player player, ItemStack item, UseAnim anim) {
        // If the usage animation is drinking, deal damage to the player.
        if (anim == UseAnim.DRINK) {
            player.hurt(FineDamageSources.waterDamage(player), fullDamage);
        }
    }

    @Override
    public void onSplashPotionHit(Player player, ThrownPotion potion) {
        // If a potion shattered near the player
        player.hurt(FineDamageSources.waterDamage(player), fullDamage);
    }

    @Override
    public Component getTitle() {
        return Component.translatable("ability.finesse_races.water_damage.title");
    }

    @Override
    public Component getDescription() {
        return Component.translatable("ability.finesse_races.water_damage.desc");
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