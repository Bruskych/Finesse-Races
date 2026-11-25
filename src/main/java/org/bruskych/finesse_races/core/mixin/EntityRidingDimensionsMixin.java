package org.bruskych.finesse_races.core.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

import org.bruskych.finesse_races.gameplay.races.core.RaceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Forces {@link Entity#refreshDimensions()} when a player mounts or dismounts a vehicle.
 * Vanilla keeps {@code Pose.STANDING} while riding and would otherwise retain standing
 * hitboxes and eye height instead of applying seated race dimensions.
 */
@Mixin(Entity.class)
public abstract class EntityRidingDimensionsMixin {

    /**
     * Recalculates player dimensions immediately after successfully mounting a vehicle.
     */
    @Inject(method = "startRiding(Lnet/minecraft/world/entity/Entity;Z)Z", at = @At("RETURN"))
    private void finesseRaces$refreshDimensionsOnMount(
            Entity vehicle,
            boolean force,
            CallbackInfoReturnable<Boolean> cir
    ) {
        if (!cir.getReturnValue()) {
            return;
        }
        if ((Object) this instanceof Player player
                && !player.isRemoved()
                && RaceManager.getRaceOfPlayer(player) != null) {
            player.refreshDimensions();
        }
    }

    /**
     * Recalculates player dimensions after the vehicle reference is cleared on dismount.
     */
    @Inject(
            method = "removeVehicle",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Entity;removePassenger(Lnet/minecraft/world/entity/Entity;)V",
                    shift = At.Shift.AFTER
            )
    )
    private void finesseRaces$refreshDimensionsOnDismount(CallbackInfo callbackInfo) {
        if ((Object) this instanceof Player player
                && !player.isRemoved()
                && RaceManager.getRaceOfPlayer(player) != null) {
            player.refreshDimensions();
        }
    }
}