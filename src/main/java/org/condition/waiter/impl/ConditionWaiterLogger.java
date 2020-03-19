package org.condition.waiter.impl;

import org.condition.waiter.Result;
import org.condition.waiter.Waiter;
import org.condition.waiter.matcher.ConditionMatcher;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.function.BiPredicate;

import static org.condition.waiter.Status.SUCCESS;

/**
 * ConditionWaiter decorator class with logging functionality
 */
public final class ConditionWaiterLogger<T> extends ConditionWaiter<T> {

    private static final Logger LOGGER = Logger.getLogger(Waiter.class.getSimpleName());
    private static final String PERFORM_MESSAGE = "PERFORMING ACTION: %s";

    private final ConditionWaiter<T> conditionWaiter;

    public ConditionWaiterLogger(final ConditionWaiter<T> conditionWaiter) {
        super(conditionWaiter.getAction(), conditionWaiter.getActionDescription());
        this.conditionWaiter = conditionWaiter;
    }

    @Override
    public Result<T> until(final BiPredicate<T, T> condition, final T value) {
        logBeforeActionWaitDetails();
        Result<T> result = conditionWaiter.until(condition, value);
        logAfterActionWaitDetails(result);
        return result;
    }

    @Override
    public Result<T> until(final ConditionMatcher<T> conditionMatcher, final T value) {
        logBeforeActionWaitDetails();
        Result<T> result = conditionWaiter.until(conditionMatcher, value);
        logAfterActionWaitDetails(result);
        return result;
    }

    @Override
    public Waiter<T> atMost(final long timeout, final TimeUnit unit) {
        return conditionWaiter.atMost(timeout, unit);
    }

    @Override
    public Waiter<T> withPollDelay(final long pollDelay, final TimeUnit unit) {
        return conditionWaiter.withPollDelay(pollDelay, unit);
    }

    private void logBeforeActionWaitDetails() {
        LOGGER.info(String.format(PERFORM_MESSAGE, conditionWaiter.getActionDescription()));
    }

    private void logAfterActionWaitDetails(final Result<T> result) {
        if (result.getStatus().equals(SUCCESS)) {
            LOGGER.info(result.getMessage());
        } else {
            LOGGER.error(result.getMessage());
        }
    }
}