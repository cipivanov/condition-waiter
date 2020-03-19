package org.condition.waiter.matcher.impl;

import org.condition.waiter.matcher.ConditionMatcher;

public class Contains extends ConditionMatcher<String> {

    public Contains() {
        super(String::contains, "CONTAINS");
    }
}