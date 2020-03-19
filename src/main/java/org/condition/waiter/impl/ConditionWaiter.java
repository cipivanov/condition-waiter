package org.condition.waiter.impl;

import org.condition.waiter.Result;
import org.condition.waiter.Status;
import org.condition.waiter.Waiter;
import org.condition.waiter.matcher.ConditionMatcher;
import org.condition.waiter.matcher.impl.CustomMatcher;
import org.awaitility.Duration;
import org.awaitility.core.ConditionFactory;
import org.awaitility.core.ConditionTimeoutException;

import java.util.concurrent.TimeUnit;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.condition.waiter.Status.FAILURE;
import static org.condition.waiter.Status.SUCCESS;
import static org.awaitility.Awaitility.await;

/**
 * Implements the org.condition.waiter.Waiter interface to evaluate an condition (supplier) against a condition (predicate)
 */
public class ConditionWaiter<T> implements Waiter<T> {

    private final Supplier<T> action;
    private String actionDescription;
    private Duration timeout = new Duration(5, SECONDS);
    private Duration pollDelay = new Duration(1, SECONDS);

    public ConditionWaiter(final Supplier<T> action, final String actionDescription) {
        checkIfNullOrEmpty(actionDescription);
        this.action = action;
        this.actionDescription = actionDescription;
    }

    /**
     * Triggers the condition wait and returns it's result
     *
     * @param action            condition that will be repeatedly executed while evaluating the condition
     * @param actionDescription string representation of the condition being provided. For logging readability purposes.
     * @return condition wait result
     */
    public static <T> Waiter<T> perform(final Supplier<T> action, final String actionDescription) {
        return new ConditionWaiterLogger<>(new ConditionWaiter<>(action, actionDescription));
    }

    public Supplier<T> getAction() {
        return action;
    }

    public String getActionDescription() {
        return actionDescription;
    }

    public Duration getTimeout() {
        return timeout;
    }

    public Duration getPollDelay() {
        return pollDelay;
    }

    @Override
    public Result<T> until(final BiPredicate<T, T> condition, final T value) {
        return until(new CustomMatcher<>(condition), value);
    }

    @Override
    public Result<T> until(final ConditionMatcher<T> conditionMatcher, final T value) {
        return waitUntil(conditionMatcher.getCondition(), value, conditionMatcher.getConditionDescription());
    }

    @Override
    public Waiter<T> atMost(final long timeout, final TimeUnit unit) {
        this.timeout = new Duration(timeout, unit);
        return new ConditionWaiterLogger<>(this);
    }

    @Override
    public Waiter<T> withPollDelay(final long pollDelay, final TimeUnit unit) {
        timeout = new Duration(pollDelay, unit);
        return new ConditionWaiterLogger<>(this);
    }

    private Result<T> waitUntil(final BiPredicate<T, T> condition, final T expectedValue,
                                final String conditionDescription) {
        Result<T> result;
        try {
            defaultAwait().until(() -> condition.test(action.get(), expectedValue));
            result = createResult(actionDescription, conditionDescription, SUCCESS, expectedValue);
        } catch (ConditionTimeoutException ex) {
            result = createResult(actionDescription, conditionDescription, FAILURE, expectedValue);
        }
        return result;
    }

    /**
     * Uses Awaitility library (https://github.com/awaitility/awaitility/blob/master/LICENSE) licensed under Apache 2.0.
     * (c) Apache Software Foundation
     */
    private ConditionFactory defaultAwait() {
        return await().with().pollDelay(pollDelay).atMost(timeout);
    }

    private Result<T> createResult(final String actionDescription, final String conditionDescription,
                                   final Status status, final T expectedValue) {
        return new Result<>(actionDescription, conditionDescription, status, action.get(), expectedValue);
    }

    private void checkIfNullOrEmpty(String actionDescription) {
        if (actionDescription == null || actionDescription.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty or null. Please provide a description");
        }
    }
}