package org.condition.waiter.matcher.impl;

import org.condition.waiter.matcher.ConditionMatcher;
import org.apache.commons.lang.StringUtils;

import java.util.function.BiPredicate;

/**
 * Matcher for custom conditions without a description
 */
public class CustomMatcher<T> extends ConditionMatcher<T> {

    public CustomMatcher(final BiPredicate<T, T> condition) {
        super(condition, StringUtils.EMPTY);
    }
}