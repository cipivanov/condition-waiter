package org.action.waiter.matcher.impl;

import org.action.waiter.matcher.ConditionMatcher;

public class IsEqualTo<T> extends ConditionMatcher<T> {

    public IsEqualTo() {
        super(Object::equals, "EQUAL to");
    }
}