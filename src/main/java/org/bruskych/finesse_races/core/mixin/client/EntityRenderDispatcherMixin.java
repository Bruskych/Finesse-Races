package org.bruskych.finesse_races.core.mixin.client;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;

import org.bruskych.finesse_races.gameplay.races.core.RaceManager;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Normalizes the 3D burning flame effect for players across custom race sizes.
 * Caps tall or wide races at vanilla proportions to prevent stretched fire,
 * while allowing smaller races to shrink the flame with their hitbox.
 */
@Mixin(EntityRenderDispatcher.class)
public abstract class EntityRenderDispatcherMixin {

    /**
     * Caps flame width at the vanilla player width for the current pose.
     */
    @Redirect(
            method = "renderFlame",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;getBbWidth()F")
    )
    private float finesseRaces$clampFlameWidth(Entity entity) {
        float actualWidth = entity.getBbWidth();
        if (entity instanceof Player player && RaceManager.getRaceOfPlayer(player) != null) {
            return Math.min(actualWidth, finesseRaces$getVanillaPlayerWidth(player.getPose()));
        }
        return actualWidth;
    }

    /**
     * Caps flame height at the vanilla player height for the current pose.
     */
    @Redirect(
            method = "renderFlame",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;getBbHeight()F")
    )
    private float finesseRaces$clampFlameHeight(Entity entity) {
        float actualHeight = entity.getBbHeight();
        if (entity instanceof Player player && RaceManager.getRaceOfPlayer(player) != null) {
            return Math.min(actualHeight, finesseRaces$getVanillaPlayerHeight(player.getPose()));
        }
        return actualHeight;
    }

    /**
     * Returns the vanilla player bounding box width for the given pose.
     */
    @Unique
    private static float finesseRaces$getVanillaPlayerWidth(Pose pose) {
        if (pose == Pose.SLEEPING || pose == Pose.DYING) {
            return 0.2F;
        }
        return 0.6F;
    }

    /**
     * Returns the vanilla player bounding box height for the given pose.
     */
    @Unique
    private static float finesseRaces$getVanillaPlayerHeight(Pose pose) {
        if (pose == Pose.CROUCHING) {
            return 1.5F;
        }
        if (pose == Pose.SWIMMING || pose == Pose.FALL_FLYING || pose == Pose.SPIN_ATTACK) {
            return 0.6F;
        }
        if (pose == Pose.SLEEPING || pose == Pose.DYING) {
            return 0.2F;
        }
        return 1.8F;
    }
}