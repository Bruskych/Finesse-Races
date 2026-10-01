package org.bruskych.finesse_races.gameplay.abilities;

/**
 * Ability categories.
 * Any created ability must belong to a specific category.
 */
public enum AbilityCategory {
    BUFF(0),
    NEUTRAL(1),
    DEBUFF(2);

    private final int priority;

    AbilityCategory(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}