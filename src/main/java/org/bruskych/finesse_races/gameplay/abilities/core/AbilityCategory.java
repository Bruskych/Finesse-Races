package org.bruskych.finesse_races.gameplay.abilities.core;

/**
 * Ability categories.
 * Any created ability must belong to a specific category.
 */
public enum AbilityCategory {
    BENEFICIAL(0),
    NEUTRAL(1),
    HARMFUL(2);

    private final int priority;

    AbilityCategory(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}