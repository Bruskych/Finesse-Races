package org.bruskych.finesse_races.core.mixin;

import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;

import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;
import org.bruskych.finesse_races.gameplay.races.core.RaceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Overrides player dimensions and camera eye height using the active race's precomputed profile.
 * Injected directly into {@link Player} so collision, ceiling checks, and camera stay in sync.
 */
@Mixin(Player.class)
public abstract class PlayerSizeMixin {

    /**
     * Applies race-specific hitbox dimensions for the requested pose.
     */
    @Inject(method = "getDimensions", at = @At("RETURN"), cancellable = true)
    private void finesseRaces$applyDimensions(Pose pose, CallbackInfoReturnable<EntityDimensions> callbackInfo) {
        Player player = (Player) (Object) this;
        AbstractRace race = RaceManager.getRaceOfPlayer(player);
        if (race == null) {
            return;
        }

        EntityDimensions customDimensions = race.getDimensionsProfile().getDimensions(pose);
        if (customDimensions != null) {
            callbackInfo.setReturnValue(customDimensions);
        }
    }

    /**
     * Applies race-specific camera eye height for the requested pose.
     */
    @Inject(method = "getStandingEyeHeight", at = @At("RETURN"), cancellable = true)
    private void finesseRaces$applyEyeHeight(Pose pose, EntityDimensions dimensions, CallbackInfoReturnable<Float> callbackInfo) {
        Player player = (Player) (Object) this;
        AbstractRace race = RaceManager.getRaceOfPlayer(player);
        if (race == null) {
            return;
        }

        float vanillaEyeHeight = callbackInfo.getReturnValue();
        float customEyeHeight = race.getDimensionsProfile().applyEyeHeight(pose, vanillaEyeHeight);

        if (Float.compare(customEyeHeight, vanillaEyeHeight) != 0) {
            callbackInfo.setReturnValue(customEyeHeight);
        }
    }
}