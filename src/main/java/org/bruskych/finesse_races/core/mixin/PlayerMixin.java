package org.bruskych.finesse_races.core.mixin;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import org.bruskych.finesse_races.core.init.FineSounds;
import org.bruskych.finesse_races.gameplay.damage.FineDamageTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Implements custom sound playback for custom damage types.
 */
@Mixin(Player.class)
public abstract class PlayerMixin {

    @Inject(method = "getHurtSound", at = @At("HEAD"), cancellable = true)
    private void finesse_races$onGetHurtSound(DamageSource source, CallbackInfoReturnable<SoundEvent> cir) {

        // Check: Was damage dealt by our custom source?
        if (source.is(FineDamageTypes.WATER_DAMAGE)) {
            cir.setReturnValue(FineSounds.WATER_DAMAGE_HURT.get());
        }
    }
}
