package org.condition.waiter.matcher.impl;

import org.condition.waiter.matcher.ConditionMatcher;

public class IsEqualTo<T> extends ConditionMatcher<T> {

    public IsEqualTo() {
        super(Object::equals, "EQUAL to");
    }
}