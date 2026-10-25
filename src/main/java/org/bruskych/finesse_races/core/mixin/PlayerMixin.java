package org.bruskych.finesse_races.core.mixin;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;

import org.bruskych.finesse_races.core.init.FineSounds;
import org.bruskych.finesse_races.gameplay.conditions.PlayerConditionHandler;
import org.bruskych.finesse_races.gameplay.damage.FineDamageTypes;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin for the Player entity.
 * Handles custom damage sounds and aggro cancellation.
 */
@Mixin(Player.class)
public abstract class PlayerMixin {

    /**
     * Plays a custom sound when the player takes water damage.
     */
    @Inject(method = "getHurtSound", at = @At("HEAD"), cancellable = true)
    private void finesse_races$onGetHurtSound(DamageSource source, CallbackInfoReturnable<SoundEvent> cir) {
        if (source.is(FineDamageTypes.WATER_DAMAGE)) {
            cir.setReturnValue(FineSounds.WATER_DAMAGE_HURT.get());
        }
    }

    /**
     * Prevents mobs from seeing the player as an enemy while selecting a race.
     */
    @Inject(method = "canBeSeenAsEnemy", at = @At("HEAD"), cancellable = true)
    private void finesseRaces$preventMobAggroWhileSelecting(CallbackInfoReturnable<Boolean> cir) {
        Player self = (Player) (Object) this;
        if (PlayerConditionHandler.isSelectingRace(self)) {
            cir.setReturnValue(false);
        }
    }
}