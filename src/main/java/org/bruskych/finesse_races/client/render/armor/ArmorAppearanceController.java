package org.bruskych.finesse_races.client.render.armor;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;

import org.bruskych.finesse_races.client.render.ModelPartTransformHelper;
import org.bruskych.finesse_races.gameplay.races.appearance.ArmorFitProfile;
import org.bruskych.finesse_races.gameplay.races.appearance.PlayerAppearanceProfile;
import org.bruskych.finesse_races.gameplay.races.appearance.PlayerBodyPart;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;
import org.bruskych.finesse_races.gameplay.races.core.RaceManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Zero-allocation, re-entrant controller for aligning player armor to custom race proportions.
 * <p>
 * Preserves vanilla 1:1 armor proportions (preventing stretching on any slot) while aligning
 * helmets to custom head heights, keeping leggings waistbands anchored at the hips, and
 * shifting boots along each leg's local axis to match custom leg lengths.
 */
public final class ArmorAppearanceController {

    private static final int INITIAL_POOL_CAPACITY = 8;
    private static final List<IArmorAppearanceCompat> COMPAT_ADAPTERS = new ArrayList<>();
    private static ArmorRenderState[] statePool = createInitialPool();
    private static int stackDepth = 0;

    private ArmorAppearanceController() {
    }

    /**
     * Pre-allocates the initial pool of reusable render states to avoid per-frame allocations.
     */
    private static ArmorRenderState[] createInitialPool() {
        ArmorRenderState[] pool = new ArmorRenderState[INITIAL_POOL_CAPACITY];
        for (int i = 0; i < pool.length; i++) {
            pool[i] = new ArmorRenderState();
        }
        return pool;
    }

    /**
     * Doubles the capacity of the state pool if deeply nested render calls exceed the current limit.
     */
    private static void growPool() {
        int oldLength = statePool.length;
        ArmorRenderState[] expanded = Arrays.copyOf(statePool, oldLength * 2);
        for (int i = oldLength; i < expanded.length; i++) {
            expanded[i] = new ArmorRenderState();
        }
        statePool = expanded;
    }

    /**
     * Registers a compatibility adapter for non-standard modded armor models (e.g., GeckoLib).
     *
     * @param adapter the armor compatibility adapter to register; ignored if {@code null}
     */
    public static void registerCompat(IArmorAppearanceCompat adapter) {
        if (adapter != null) {
            COMPAT_ADAPTERS.add(adapter);
        }
    }

    /**
     * Reserves a clean state frame at the start of {@code HumanoidArmorLayer#renderArmorPiece}.
     * Must always be paired with {@link #endSlot()}.
     */
    public static void beginSlot() {
        if (stackDepth == statePool.length) {
            growPool();
        }
        statePool[stackDepth++].clear();
    }

    /**
     * Captures the current armor model state and applies non-stretching race alignment
     * for the active equipment slot.
     *
     * @param player the player currently being rendered
     * @param model  the armor model resolved by Forge's armor model hook
     * @param slot   the equipment slot being rendered
     */
    public static void applyArmorFit(Player player, Model model, EquipmentSlot slot) {
        if (stackDepth == 0) {
            return;
        }

        AbstractRace race = RaceManager.getRaceOfPlayer(player);
        if (race == null) {
            return;
        }

        PlayerAppearanceProfile profile = race.getAppearanceProfile();
        if (profile.isEmpty()) {
            return;
        }

        ArmorRenderState state = statePool[stackDepth - 1];

        // Delegate to the first matching modded armor compat adapter, if any
        if (!COMPAT_ADAPTERS.isEmpty()) {
            for (IArmorAppearanceCompat adapter : COMPAT_ADAPTERS) {
                if (adapter.supports(model)) {
                    adapter.apply(player, model, profile, slot);
                    state.captureCompat(adapter, model, slot);
                    return;
                }
            }
        }

        if (!(model instanceof HumanoidModel<?> humanoidModel)) {
            return;
        }

        // Snapshot shared vanilla armor parts before modifying them for this render pass
        state.captureHumanoid(humanoidModel);

        // Enforce 1:1 vanilla scale on all armor parts so copyPropertiesTo never stretches armor
        resetScale(humanoidModel.head);
        resetScale(humanoidModel.hat);
        resetScale(humanoidModel.body);
        resetScale(humanoidModel.rightArm);
        resetScale(humanoidModel.leftArm);
        resetScale(humanoidModel.rightLeg);
        resetScale(humanoidModel.leftLeg);

        switch (slot) {
            case HEAD -> {
                // Shift helmet upward along the head's local Y axis to sit cleanly on taller/shorter heads
                float headLengthDelta = profile.getPart(PlayerBodyPart.HEAD).getLengthDelta();
                if (headLengthDelta != 0.0F) {
                    ModelPartTransformHelper.shiftAlongLocalYAxis(humanoidModel.head, -headLengthDelta);
                    ModelPartTransformHelper.shiftAlongLocalYAxis(humanoidModel.hat, -headLengthDelta);
                }
            }
            case LEGS -> {
                // Cancel torso vertical shift so the leggings waistband stays anchored at the hips (Y = 12)
                float bodyLengthDelta = profile.getPart(PlayerBodyPart.BODY).getLengthDelta();
                if (bodyLengthDelta != 0.0F) {
                    humanoidModel.body.y += bodyLengthDelta;
                }
            }
            case FEET -> {
                // Shift boots along each leg's local Y axis to align with customized feet positions
                ArmorFitProfile armorFit = profile.getArmorFitProfile();
                if (armorFit.shouldAlignBootsToFeet()) {
                    float rightOffset = profile.getPart(PlayerBodyPart.RIGHT_LEG).getLengthDelta()
                            + armorFit.getBootExtraOffsetPixels();
                    float leftOffset = profile.getPart(PlayerBodyPart.LEFT_LEG).getLengthDelta()
                            + armorFit.getBootExtraOffsetPixels();

                    if (rightOffset != 0.0F) {
                        ModelPartTransformHelper.shiftAlongLocalYAxis(humanoidModel.rightLeg, rightOffset);
                    }
                    if (leftOffset != 0.0F) {
                        ModelPartTransformHelper.shiftAlongLocalYAxis(humanoidModel.leftLeg, leftOffset);
                    }
                }
            }
            default -> {
                // Chestplate aligns naturally to customized shoulder/torso pivots via copyPropertiesTo
            }
        }
    }

    /**
     * Pops the active state frame and restores the armor model back to its pre-fit transform
     * at the end of {@code HumanoidArmorLayer#renderArmorPiece}.
     */
    public static void endSlot() {
        if (stackDepth == 0) {
            return;
        }
        statePool[--stackDepth].restoreAndClear();
    }

    /**
     * Resets a model part's scale factors to vanilla 1:1 proportions.
     */
    private static void resetScale(ModelPart part) {
        part.xScale = 1.0F;
        part.yScale = 1.0F;
        part.zScale = 1.0F;
    }

    /**
     * Reusable snapshot holding either vanilla {@link HumanoidModel} part transforms
     * or an active {@link IArmorAppearanceCompat} invocation for clean restoration.
     */
    private static final class ArmorRenderState {

        /** Floats stored per part: x, y, z, xScale, yScale, zScale. */
        private static final int PART_STRIDE = 6;
        private static final int PART_COUNT = 7;

        private final float[] savedParts = new float[PART_COUNT * PART_STRIDE];

        private HumanoidModel<?> humanoidModel;
        private IArmorAppearanceCompat compatAdapter;
        private Model compatModel;
        private EquipmentSlot compatSlot;

        /**
         * Clears object references to prevent retaining models across frames or resource reloads.
         */
        void clear() {
            this.humanoidModel = null;
            this.compatAdapter = null;
            this.compatModel = null;
            this.compatSlot = null;
        }

        /**
         * Captures position and scale of all 7 humanoid armor parts into the flat primitive buffer.
         */
        void captureHumanoid(HumanoidModel<?> model) {
            clear();
            this.humanoidModel = model;
            savePart(model.head, 0);
            savePart(model.hat, 6);
            savePart(model.body, 12);
            savePart(model.rightArm, 18);
            savePart(model.leftArm, 24);
            savePart(model.rightLeg, 30);
            savePart(model.leftLeg, 36);
        }

        /**
         * Records an active compatibility adapter invocation so it can be reset after rendering.
         */
        void captureCompat(IArmorAppearanceCompat adapter, Model model, EquipmentSlot slot) {
            clear();
            this.compatAdapter = adapter;
            this.compatModel = model;
            this.compatSlot = slot;
        }

        /**
         * Restores the previously captured humanoid model or compat adapter state and clears references.
         */
        void restoreAndClear() {
            HumanoidModel<?> humanoid = this.humanoidModel;
            if (humanoid != null) {
                this.humanoidModel = null;
                restorePart(humanoid.head, 0);
                restorePart(humanoid.hat, 6);
                restorePart(humanoid.body, 12);
                restorePart(humanoid.rightArm, 18);
                restorePart(humanoid.leftArm, 24);
                restorePart(humanoid.rightLeg, 30);
                restorePart(humanoid.leftLeg, 36);
                return;
            }

            IArmorAppearanceCompat adapter = this.compatAdapter;
            if (adapter != null) {
                Model model = this.compatModel;
                EquipmentSlot slot = this.compatSlot;
                clear();
                adapter.reset(model, slot);
            }
        }

        /**
         * Stores a single {@link ModelPart}'s translation and scale into {@link #savedParts}.
         */
        private void savePart(ModelPart part, int offset) {
            this.savedParts[offset] = part.x;
            this.savedParts[offset + 1] = part.y;
            this.savedParts[offset + 2] = part.z;
            this.savedParts[offset + 3] = part.xScale;
            this.savedParts[offset + 4] = part.yScale;
            this.savedParts[offset + 5] = part.zScale;
        }

        /**
         * Restores a single {@link ModelPart}'s translation and scale from {@link #savedParts}.
         */
        private void restorePart(ModelPart part, int offset) {
            part.x = this.savedParts[offset];
            part.y = this.savedParts[offset + 1];
            part.z = this.savedParts[offset + 2];
            part.xScale = this.savedParts[offset + 3];
            part.yScale = this.savedParts[offset + 4];
            part.zScale = this.savedParts[offset + 5];
        }
    }
}