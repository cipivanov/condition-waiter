package org.condition.waiter.matcher;

import java.util.function.BiPredicate;

/**
 * Represents and provides a description for common conditions (predicates)
 */
public abstract class ConditionMatcher<T> {

    private BiPredicate<T, T> condition;
    private String conditionDescription;

    public ConditionMatcher(final BiPredicate<T, T> condition, final String conditionDescription) {
        this.condition = condition;
        this.conditionDescription = conditionDescription;
    }

    public BiPredicate<T, T> getCondition() {
        return condition;
    }

    public String getConditionDescription() {
        return conditionDescription;
    }
}