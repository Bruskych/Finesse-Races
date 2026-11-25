package org.bruskych.finesse_races.client.render.player;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

import org.bruskych.finesse_races.client.render.ModelPartTransformHelper;
import org.bruskych.finesse_races.gameplay.races.appearance.PlayerAppearanceProfile;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;
import org.bruskych.finesse_races.gameplay.races.core.RaceManager;

import java.util.Arrays;

/**
 * Zero-allocation, re-entrant controller for first-person hand rendering inside {@code PlayerRenderer#renderHand}.
 * <p>
 * Tracks active first-person render depth so third-person mixins stay out of the way,
 * suppresses vanilla arm draw calls non-destructively when custom race geometry is active,
 * and renders or restores race-adapted arms cleanly without breaking hand animation mods.
 */
public final class FirstPersonHandController {

    private static final int INITIAL_POOL_CAPACITY = 4;

    private static HandContext[] contextPool = createInitialPool();
    private static int activeDepth = 0;

    private FirstPersonHandController() {
    }

    /**
     * Pre-allocates the initial pool of reusable hand contexts to avoid per-frame allocations.
     */
    private static HandContext[] createInitialPool() {
        HandContext[] pool = new HandContext[INITIAL_POOL_CAPACITY];
        for (int i = 0; i < pool.length; i++) {
            pool[i] = new HandContext();
        }
        return pool;
    }

    /**
     * Doubles the pool capacity if nested first-person hand renders exceed the current limit.
     */
    private static void growPool() {
        int oldLength = contextPool.length;
        HandContext[] expanded = Arrays.copyOf(contextPool, oldLength * 2);
        for (int i = oldLength; i < expanded.length; i++) {
            expanded[i] = new HandContext();
        }
        contextPool = expanded;
    }

    /**
     * Marks entry into {@code PlayerRenderer#renderHand} and reserves a clean {@link HandContext}.
     * Must always be paired with {@link #finishArm}.
     */
    public static void enter() {
        if (activeDepth == contextPool.length) {
            growPool();
        }
        contextPool[activeDepth++].clear();
    }

    /**
     * Returns {@code true} while the client is inside a first-person hand render pass.
     * Used by third-person model mixins to avoid applying world-space body adjustments to the camera hand.
     */
    public static boolean isActive() {
        return activeDepth > 0;
    }

    /**
     * Prepares the first-person arm immediately before vanilla's first {@link ModelPart#render} call.
     * <p>
     * For races with custom arm geometry and a resolved arm texture, hides the vanilla arm and sleeve
     * while capturing their visibility state. For races that resize vanilla arms without replacing them,
     * snapshots and modifies their Y position and scale directly.
     *
     * @param renderer        the active {@link PlayerRenderer} instance
     * @param player          the local client player whose arm is being rendered
     * @param rendererArm     the vanilla base arm {@link ModelPart}
     * @param rendererArmwear the vanilla sleeve overlay {@link ModelPart}
     */
    public static void prepareArm(
            PlayerRenderer renderer,
            AbstractClientPlayer player,
            ModelPart rendererArm,
            ModelPart rendererArmwear
    ) {
        if (activeDepth == 0) {
            return;
        }

        AbstractRace race = RaceManager.getRaceOfPlayer(player);
        if (race == null) {
            return;
        }

        PlayerAppearanceProfile profile = race.getAppearanceProfile();
        if (!profile.hasVisualModifications()) {
            return;
        }

        boolean isRightArm = (rendererArm == renderer.getModel().rightArm);
        boolean slim = "slim".equals(player.getModelName());

        // Abort early if the race expects custom arm geometry but no arm texture is available
        ResourceLocation armsTexture = null;
        if (profile.replacesArms()) {
            armsTexture = RaceSkinResolver.resolve(player, race, slim).armsTexture();
            if (armsTexture == null) {
                return;
            }
        }

        ArmTransformCalculator.FirstPersonArmTransform fpTransform =
                ArmTransformCalculator.computeFirstPersonArm(player, profile, isRightArm, slim);

        HandContext ctx = contextPool[activeDepth - 1];

        if (armsTexture != null) {
            // Suppress vanilla draw calls non-destructively so other mods' hooks still execute
            ctx.replaceCustom = true;
            ctx.race = race;
            ctx.armsTexture = armsTexture;
            ctx.isRightArm = isRightArm;
            ctx.slim = slim;
            ctx.fpTransform = fpTransform;
            ctx.armWasVisible = rendererArm.visible;
            ctx.sleeveWasVisible = rendererArmwear.visible;

            rendererArm.visible = false;
            rendererArmwear.visible = false;
            return;
        }

        // Fallback for races that resize arms without replacing arm textures
        if (!fpTransform.isIdentity()) {
            ctx.scaleVanilla = true;
            ctx.armY = rendererArm.y;
            ctx.armXScale = rendererArm.xScale;
            ctx.armYScale = rendererArm.yScale;
            ctx.armZScale = rendererArm.zScale;

            ctx.sleeveY = rendererArmwear.y;
            ctx.sleeveXScale = rendererArmwear.xScale;
            ctx.sleeveYScale = rendererArmwear.yScale;
            ctx.sleeveZScale = rendererArmwear.zScale;

            rendererArm.y += fpTransform.offsetYPixels();
            rendererArm.xScale = fpTransform.scaleX();
            rendererArm.yScale = fpTransform.scaleY();
            rendererArm.zScale = fpTransform.scaleZ();

            rendererArmwear.y += fpTransform.offsetYPixels();
            rendererArmwear.xScale = fpTransform.scaleX();
            rendererArmwear.yScale = fpTransform.scaleY();
            rendererArmwear.zScale = fpTransform.scaleZ();
        }
    }

    /**
     * Completes the first-person hand render pass at the end of {@code PlayerRenderer#renderHand}.
     * <p>
     * Restores vanilla visibility and draws the custom baked arm and sleeve if replaced,
     * or restores the original vanilla Y position and scale factors if scaled, then pops
     * and clears the active context frame.
     *
     * @param poseStack       the current render {@link PoseStack}
     * @param buffer          the {@link MultiBufferSource} used to obtain the arm vertex consumer
     * @param combinedLight   packed lightmap coordinates for the hand render
     * @param rendererArm     the vanilla base arm {@link ModelPart}
     * @param rendererArmwear the vanilla sleeve overlay {@link ModelPart}
     */
    public static void finishArm(
            PoseStack poseStack,
            MultiBufferSource buffer,
            int combinedLight,
            ModelPart rendererArm,
            ModelPart rendererArmwear
    ) {
        try {
            if (activeDepth == 0) {
                return;
            }
            HandContext ctx = contextPool[activeDepth - 1];

            if (ctx.replaceCustom) {
                rendererArm.visible = ctx.armWasVisible;
                rendererArmwear.visible = ctx.sleeveWasVisible;

                RaceLimbGeometryBuilder.BakedRaceLimbs baked = RaceModelCache.getOrBuild(ctx.race, ctx.slim);
                ModelPart customArm = ctx.isRightArm ? baked.rightArm() : baked.leftArm();
                ModelPart customSleeve = ctx.isRightArm ? baked.rightSleeve() : baked.leftSleeve();

                if (customArm != null) {
                    VertexConsumer consumer = buffer.getBuffer(RenderType.entityTranslucent(ctx.armsTexture));

                    if (ctx.armWasVisible) {
                        customArm.copyFrom(rendererArm);
                        ModelPartTransformHelper.shiftAlongLocalYAxis(customArm, ctx.fpTransform.offsetYPixels());
                        customArm.render(poseStack, consumer, combinedLight, OverlayTexture.NO_OVERLAY);
                    }

                    if (customSleeve != null && ctx.sleeveWasVisible) {
                        customSleeve.copyFrom(rendererArmwear);
                        ModelPartTransformHelper.shiftAlongLocalYAxis(customSleeve, ctx.fpTransform.offsetYPixels());
                        customSleeve.render(poseStack, consumer, combinedLight, OverlayTexture.NO_OVERLAY);
                    }
                }
            } else if (ctx.scaleVanilla) {
                rendererArm.y = ctx.armY;
                rendererArm.xScale = ctx.armXScale;
                rendererArm.yScale = ctx.armYScale;
                rendererArm.zScale = ctx.armZScale;

                rendererArmwear.y = ctx.sleeveY;
                rendererArmwear.xScale = ctx.sleeveXScale;
                rendererArmwear.yScale = ctx.sleeveYScale;
                rendererArmwear.zScale = ctx.sleeveZScale;
            }
        } finally {
            if (activeDepth > 0) {
                contextPool[--activeDepth].clear();
            }
        }
    }

    /**
     * Reusable per-invocation state holder for a single {@code PlayerRenderer#renderHand} call.
     */
    private static final class HandContext {
        private boolean replaceCustom;
        private boolean scaleVanilla;
        private AbstractRace race;
        private ResourceLocation armsTexture;
        private boolean isRightArm;
        private boolean slim;
        private ArmTransformCalculator.FirstPersonArmTransform fpTransform;
        private boolean armWasVisible;
        private boolean sleeveWasVisible;

        private float armY, armXScale, armYScale, armZScale;
        private float sleeveY, sleeveXScale, sleeveYScale, sleeveZScale;

        /**
         * Resets flags and releases object references before returning this slot to the pool.
         */
        private void clear() {
            this.replaceCustom = false;
            this.scaleVanilla = false;
            this.race = null;
            this.armsTexture = null;
            this.fpTransform = null;
        }
    }
}