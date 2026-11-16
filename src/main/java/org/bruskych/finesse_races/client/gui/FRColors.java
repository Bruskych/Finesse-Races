package org.bruskych.finesse_races.client.gui;

/**
 * A central registry for all GUI colors used in the mod.
 */
public final class FRColors {

    private FRColors() {}

    public static final int WHITE = 0xFFFFFF;
    public static final int DARK_GRAY = 0x404040;
    public static final int INACTIVE_GRAY = 0x555555;
    public static final int POSITIVE_GREEN = 0x55FF55;
    public static final int NEGATIVE_RED = 0xFF5555;

    public static int getTraitColor(double value) {
        if (value > 0.0D) return POSITIVE_GREEN;
        if (value < 0.0D) return NEGATIVE_RED;
        return WHITE;
    }
}