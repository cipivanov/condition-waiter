package org.action.waiter;

import org.action.waiter.matcher.ConditionMatcher;

import java.util.concurrent.TimeUnit;
import java.util.function.BiPredicate;

/**
 * Evaluates an action (supplier) against a condition (predicate)
 */
public interface Waiter<T> {

    /**
     * Triggers the condition wait and returns it's result
     *
     * @param condition the predicate that will be checked until either timeout is reached or it is evaluated as true
     * @param value     value to test the predicate against - "expected value"
     * @return condition wait result
     */
    Result<T> until(final BiPredicate<T, T> condition, final T value);

    /**
     * Triggers the condition wait and returns it's result
     *
     * @param conditionMatcher represents common condition and it's description
     * @param value            value to test the predicate against - "expected value"
     * @return condition wait result
     */
    Result<T> until(final ConditionMatcher<T> conditionMatcher, final T value);

    /**
     * Overwrites the default timeout before returning a FAILURE
     *
     * @param timeout amount of time units to wait for
     * @param unit    time unit to wait for e.g. SECONDS, MINUTES etc.
     * @return calling org.action.waiter.Waiter object with overwritten timeout
     */
    Waiter<T> atMost(final long timeout, final TimeUnit unit);

    /**
     * Overwrites the default poll delay between any two condition checks
     *
     * @param pollDelay amount of time units to wait for
     * @param unit      time unit to wait for e.g. SECONDS, MINUTES etc.
     * @return calling org.action.waiter.Waiter object with overwritten poll delay
     */
    Waiter<T> withPollDelay(final long pollDelay, final TimeUnit unit);
}