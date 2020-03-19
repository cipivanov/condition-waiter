package org.action.waiter.matcher.impl;

import org.action.waiter.matcher.ConditionMatcher;

public class IsNotEqualTo<T> extends ConditionMatcher<T> {

    public IsNotEqualTo() {
        super((actual, expected) -> !actual.equals(expected), "NOT EQUAL to");
    }
}