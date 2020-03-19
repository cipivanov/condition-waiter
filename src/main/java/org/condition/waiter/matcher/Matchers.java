package org.condition.waiter.matcher;

import org.condition.waiter.matcher.impl.Contains;
import org.condition.waiter.matcher.impl.IsEqualTo;
import org.condition.waiter.matcher.impl.IsNotEqualTo;

/**
 * Aggregates the implemented ConditionMatchers
 */
public final class Matchers {

    private Matchers() {
    }

    public static <T> ConditionMatcher<T> isEqualTo() {
        return new IsEqualTo<>();
    }

    public static <T> ConditionMatcher<T> isNotEqualTo() {
        return new IsNotEqualTo<>();
    }

    public static ConditionMatcher<String> contains() {
        return new Contains();
    }
}