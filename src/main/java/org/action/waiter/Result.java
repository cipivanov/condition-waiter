package org.action.waiter;

/**
 * org.action.waiter.Result class returned by the ConditionWaiter
 */
public class Result<T> {

    private final String MESSAGE = "%s ACTION: %s, Actual: <%s>, Expected: %s <%s>";

    private final T actualValue;
    private final T expectedValue;
    private final String message;
    private final Status status;

    public Result(final String actionDesc, final String conditionDesc, final Status status,
                  final T actualValue, final T expectedValue) {
        this.actualValue = actualValue;
        this.expectedValue = expectedValue;
        this.status = status;
        this.message = String.format(MESSAGE, status, actionDesc, actualValue, conditionDesc, expectedValue);
    }

    public T getActualValue() {
        return actualValue;
    }

    public T getExpectedValue() {
        return expectedValue;
    }

    public String getMessage() {
        return message;
    }

    public Status getStatus() {
        return status;
    }
}