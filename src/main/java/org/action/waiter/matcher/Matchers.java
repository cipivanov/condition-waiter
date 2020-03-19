package org.action.waiter.matcher;

import org.action.waiter.matcher.impl.Contains;
import org.action.waiter.matcher.impl.IsEqualTo;
import org.action.waiter.matcher.impl.IsNotEqualTo;

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