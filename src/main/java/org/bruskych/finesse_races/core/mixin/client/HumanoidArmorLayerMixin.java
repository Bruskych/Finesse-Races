package org.bruskych.finesse_races.core.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import org.bruskych.finesse_races.client.render.armor.ArmorAppearanceController;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Brackets player armor slot renders with {@link ArmorAppearanceController}.
 * Hooks Forge's armor model resolution via {@code @ModifyVariable} instead of redirecting,
 * preserving compatibility with modded armor renderers.
 */
@Mixin(HumanoidArmorLayer.class)
public abstract class HumanoidArmorLayerMixin<T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> {

    /**
     * Pushes a state frame before rendering a player's armor slot.
     */
    @Inject(method = "renderArmorPiece", at = @At("HEAD"))
    private void finesseRaces$beginArmorSlot(
            PoseStack poseStack,
            MultiBufferSource buffer,
            T entity,
            EquipmentSlot slot,
            int packedLight,
            A defaultModel,
            CallbackInfo callbackInfo
    ) {
        if (entity instanceof Player) {
            ArmorAppearanceController.beginSlot();
        }
    }

    /**
     * Intercepts the armor model returned by Forge's hook and applies non-stretching race alignment.
     */
    @ModifyVariable(
            method = "renderArmorPiece",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "Lnet/minecraft/client/renderer/entity/layers/HumanoidArmorLayer;getArmorModelHook(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/client/model/HumanoidModel;)Lnet/minecraft/client/model/Model;"
            )
    )
    private Model finesseRaces$applyArmorFit(
            Model model,
            PoseStack poseStack,
            MultiBufferSource buffer,
            T entity,
            EquipmentSlot slot,
            int packedLight,
            A defaultModel
    ) {
        if (entity instanceof Player player) {
            ArmorAppearanceController.applyArmorFit(player, model, slot);
        }
        return model;
    }

    /**
     * Pops the state frame and restores the shared armor model after rendering finishes.
     */
    @Inject(method = "renderArmorPiece", at = @At("RETURN"))
    private void finesseRaces$endArmorSlot(
            PoseStack poseStack,
            MultiBufferSource buffer,
            T entity,
            EquipmentSlot slot,
            int packedLight,
            A defaultModel,
            CallbackInfo callbackInfo
    ) {
        if (entity instanceof Player) {
            ArmorAppearanceController.endSlot();
        }
    }
}