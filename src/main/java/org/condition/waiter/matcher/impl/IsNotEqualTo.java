package org.condition.waiter.matcher.impl;

import org.condition.waiter.matcher.ConditionMatcher;

public class IsNotEqualTo<T> extends ConditionMatcher<T> {

    public IsNotEqualTo() {
        super((actual, expected) -> !actual.equals(expected), "NOT EQUAL to");
    }
}