package org.bruskych.finesse_races.gameplay.races.core;

import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.ForgeMod;

import org.bruskych.finesse_races.gameplay.traits.modifiers.*;
import org.bruskych.finesse_races.gameplay.traits.modifiers.*;

import java.util.UUID;

/**
 * Handles the application and removal of Forge and Vanilla attributes based on the player's race.
 */
public class RaceAttributeManager {

    /**
     * Removes all race-specific attribute modifiers from the player.
     * Should be called when a player's race is cleared or before applying a new race.
     *
     * @param player The target player.
     */
    public static void clearRaceAttributes(Player player) {
        removeModifier(player, ForgeMod.STEP_HEIGHT_ADDITION.get(), IStepHeightModifier.STEP_HEIGHT_MODIFIER_ID);
        removeModifier(player, ForgeMod.BLOCK_REACH.get(), IBlockReachModifier.BLOCK_REACH_MODIFIER_ID);
        removeModifier(player, ForgeMod.ENTITY_REACH.get(), IEntityReachModifier.ENTITY_REACH_MODIFIER_ID);
        removeModifier(player, Attributes.MOVEMENT_SPEED, IMovementSpeedModifier.MOVEMENT_SPEED_MODIFIER_ID);
        removeModifier(player, ForgeMod.SWIM_SPEED.get(), ISwimSpeedModifier.SWIM_SPEED_MODIFIER_ID);
        removeModifier(player, Attributes.ATTACK_DAMAGE, IAttackDamageModifier.ATTACK_DAMAGE_MODIFIER_ID);
        removeModifier(player, Attributes.ATTACK_SPEED, IAttackSpeedModifier.ATTACK_SPEED_MODIFIER_ID);
    }

    /**
     * Safely removes a single attribute modifier by its UUID if the attribute exists on the player.
     */
    private static void removeModifier(Player player, Attribute attribute, UUID modifierId) {
        AttributeInstance attributeInstance = player.getAttribute(attribute);
        if (attributeInstance != null) {
            attributeInstance.removeModifier(modifierId);
        }
    }

    /**
     * Applies all race-specific attribute modifiers (like step height, block reach, attack damage) to the player.
     * Clears existing race modifiers first to prevent leftovers from previous races.
     *
     * @param player The target player.
     * @param race   The player's current race, or null to only clear modifiers.
     */
    public static void applyRaceAttributes(Player player, AbstractRace race) {

        // STEP HEIGHT
        AttributeInstance stepHeightAttr = player.getAttribute(ForgeMod.STEP_HEIGHT_ADDITION.get());
        if (stepHeightAttr != null) {
            stepHeightAttr.removeModifier(IStepHeightModifier.STEP_HEIGHT_MODIFIER_ID);
            double totalStepBonus = 0.0;
            for (IStepHeightModifier modifier : race.getModifiersOf(IStepHeightModifier.class)) {
                totalStepBonus += modifier.getStepHeightAddition();
            }
            if (totalStepBonus != 0.0) {
                stepHeightAttr.addPermanentModifier(new AttributeModifier(
                        IStepHeightModifier.STEP_HEIGHT_MODIFIER_ID,
                        "Finesse Race Step Height Bonus",
                        totalStepBonus,
                        AttributeModifier.Operation.ADDITION
                ));
            }
        }

        // BLOCK REACH
        AttributeInstance blockReachAttr = player.getAttribute(ForgeMod.BLOCK_REACH.get());
        if (blockReachAttr != null) {
            blockReachAttr.removeModifier(IBlockReachModifier.BLOCK_REACH_MODIFIER_ID);
            double totalBlockBonus = 0.0;
            for (IBlockReachModifier modifier : race.getModifiersOf(IBlockReachModifier.class)) {
                totalBlockBonus += modifier.getBlockReachBonus();
            }
            if (totalBlockBonus != 0.0) {
                blockReachAttr.addPermanentModifier(new AttributeModifier(
                        IBlockReachModifier.BLOCK_REACH_MODIFIER_ID,
                        "Finesse Race Block Reach Bonus",
                        totalBlockBonus,
                        AttributeModifier.Operation.ADDITION
                ));
            }
        }

        // ENTITY REACH
        AttributeInstance entityReachAttr = player.getAttribute(ForgeMod.ENTITY_REACH.get());
        if (entityReachAttr != null) {
            entityReachAttr.removeModifier(IEntityReachModifier.ENTITY_REACH_MODIFIER_ID);
            double totalEntityBonus = 0.0;
            for (IEntityReachModifier modifier : race.getModifiersOf(IEntityReachModifier.class)) {
                totalEntityBonus += modifier.getEntityReachBonus();
            }
            if (totalEntityBonus != 0.0) {
                entityReachAttr.addPermanentModifier(new AttributeModifier(
                        IEntityReachModifier.ENTITY_REACH_MODIFIER_ID,
                        "Finesse Race Entity Reach Bonus",
                        totalEntityBonus,
                        AttributeModifier.Operation.ADDITION
                ));
            }
        }

        // MOVEMENT SPEED
        AttributeInstance speedAttr = player.getAttribute(Attributes.MOVEMENT_SPEED);
        if (speedAttr != null) {
            speedAttr.removeModifier(IMovementSpeedModifier.MOVEMENT_SPEED_MODIFIER_ID);
            double totalSpeedBonus = 0.0;

            for (IMovementSpeedModifier modifier : race.getModifiersOf(IMovementSpeedModifier.class)) {
                totalSpeedBonus += modifier.getMovementSpeedBonus();
            }
            if (totalSpeedBonus != 0.0) {
                speedAttr.addPermanentModifier(new AttributeModifier(
                        IMovementSpeedModifier.MOVEMENT_SPEED_MODIFIER_ID,
                        "Finesse Race Movement Speed Bonus",
                        totalSpeedBonus,
                        AttributeModifier.Operation.MULTIPLY_BASE
                ));
            }
        }

        // SWIM SPEED
        AttributeInstance swimAttr = player.getAttribute(ForgeMod.SWIM_SPEED.get());
        if (swimAttr != null) {
            swimAttr.removeModifier(ISwimSpeedModifier.SWIM_SPEED_MODIFIER_ID);

            double totalSwimBonus = 0.0;
            for (ISwimSpeedModifier modifier : race.getModifiersOf(ISwimSpeedModifier.class)) {
                totalSwimBonus += modifier.getSwimSpeedMultiplier();
            }

            if (totalSwimBonus != 0.0) {
                swimAttr.addPermanentModifier(new AttributeModifier(
                        ISwimSpeedModifier.SWIM_SPEED_MODIFIER_ID,
                        "Finesse Race Swim Speed Bonus",
                        totalSwimBonus,
                        AttributeModifier.Operation.MULTIPLY_BASE
                ));
            }
        }

        // ATTACK DAMAGE
        AttributeInstance damageAttr = player.getAttribute(Attributes.ATTACK_DAMAGE);
        if (damageAttr != null) {
            damageAttr.removeModifier(IAttackDamageModifier.ATTACK_DAMAGE_MODIFIER_ID);

            double totalDamageBonus = 0.0;
            for (IAttackDamageModifier modifier : race.getModifiersOf(IAttackDamageModifier.class)) {
                totalDamageBonus += modifier.getAttackDamageAddition();
            }

            if (totalDamageBonus != 0.0) {
                damageAttr.addPermanentModifier(new AttributeModifier(
                        IAttackDamageModifier.ATTACK_DAMAGE_MODIFIER_ID,
                        "Finesse Race Attack Damage Bonus",
                        totalDamageBonus,
                        AttributeModifier.Operation.ADDITION
                ));
            }
        }

        // ATTACK SPEED (Vanilla Attribute)
        AttributeInstance attackSpeedAttr = player.getAttribute(Attributes.ATTACK_SPEED);
        if (attackSpeedAttr != null) {
            attackSpeedAttr.removeModifier(IAttackSpeedModifier.ATTACK_SPEED_MODIFIER_ID);

            double totalAttackSpeedBonus = 0.0;
            for (IAttackSpeedModifier modifier : race.getModifiersOf(IAttackSpeedModifier.class)) {
                totalAttackSpeedBonus += modifier.getAttackSpeedMultiplier();
            }

            if (totalAttackSpeedBonus != 0.0) {
                attackSpeedAttr.addPermanentModifier(new AttributeModifier(
                        IAttackSpeedModifier.ATTACK_SPEED_MODIFIER_ID,
                        "Finesse Race Attack Speed Bonus",
                        totalAttackSpeedBonus,
                        AttributeModifier.Operation.MULTIPLY_BASE
                ));
            }
        }
    }
}