package org.action.waiter.matcher.impl;

import org.action.waiter.matcher.ConditionMatcher;

public class Contains extends ConditionMatcher<String> {

    public Contains() {
        super(String::contains, "CONTAINS");
    }
}