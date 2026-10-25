package org.bruskych.finesse_races.core.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import org.bruskych.finesse_races.gameplay.conditions.PlayerConditionHandler;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin for all living entities.
 */
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    /**
     * Prevents the player from being pushed by other entities while selecting a race.
     */
    @Inject(method = "isPushable", at = @At("HEAD"), cancellable = true)
    private void finesseRaces$preventPushingWhileSelecting(CallbackInfoReturnable<Boolean> cir) {

        // Check - the current entity is a player?
        if ((Object) this instanceof Player player) {
            if (PlayerConditionHandler.isSelectingRace(player)) {
                cir.setReturnValue(false);
            }
        }
    }
}