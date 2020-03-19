package org.condition.waiter;

import org.junit.Test;

import java.util.function.Supplier;

import static org.condition.waiter.impl.ConditionWaiter.perform;
import static org.condition.waiter.matcher.Matchers.isEqualTo;
import static org.condition.waiter.matcher.Matchers.isNotEqualTo;

public class ConditionWaiterTest {

    private final String EXPECTED_USERNAME = "Test";
    private final Supplier<Integer> getIdAction = () -> 100;
    private final Supplier<String> getNameAction = () -> EXPECTED_USERNAME;
    private final String GET_ID_ACTION_DESCRIPTION = "Get ID from UserMock";
    private final String GET_NAME_ACTION_DESCRIPTION = "Get NAME from UserMock";

    @Test
    public void shouldEvaluateAsPassed() {
        Result<Integer> result =
                perform(getIdAction, GET_ID_ACTION_DESCRIPTION).until(Integer::equals, 100);

        assert result.getStatus().equals(Status.SUCCESS);
    }

    @Test
    public void shouldEvaluateAsFailed() {
        Result<Integer> result =
                perform(getIdAction, GET_ID_ACTION_DESCRIPTION).until(Integer::equals, 101);

        assert result.getStatus().equals(Status.FAILURE);
    }

    @Test
    public void shouldEvaluateAsPassedConditionMatcher() {
        Result<Integer> result =
                perform(getIdAction, GET_ID_ACTION_DESCRIPTION).until(isEqualTo(), 100);

        assert result.getStatus().equals(Status.SUCCESS);
    }

    @Test
    public void shouldEvaluateAsFailedNegativeConditionMatcher() {
        Result<Integer> result =
                perform(getIdAction, GET_ID_ACTION_DESCRIPTION).until(isNotEqualTo(), 100);

        assert result.getStatus().equals(Status.FAILURE);
    }

    @Test
    public void shouldEvaluateAsPassedNegativeConditionMatcher() {
        Result<Integer> result =
                perform(getIdAction, GET_ID_ACTION_DESCRIPTION).until(isNotEqualTo(), 101);

        assert result.getStatus().equals(Status.SUCCESS);
    }

    @Test
    public void shouldEvaluateAsPassedConditionWithString() {
        Result<String> result =
                perform(getNameAction, GET_NAME_ACTION_DESCRIPTION).until(isEqualTo(), EXPECTED_USERNAME);

        assert result.getStatus().equals(Status.SUCCESS);
    }

    @Test
    public void shouldEvaluateAsFailedConditionWithString() {
        Result<String> result =
                perform(getNameAction, GET_NAME_ACTION_DESCRIPTION).until(isNotEqualTo(), EXPECTED_USERNAME);

        assert result.getStatus().equals(Status.FAILURE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrownAnIllegalArgumentException() {
        perform(getNameAction, null).until(isNotEqualTo(), EXPECTED_USERNAME);
    }
}