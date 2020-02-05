package com.github.jcestaro.objectivesmanager.model.entity;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class ObjectiveStatusTest {

    @Test
    public void shouldReturnTheExpectedNumberOfStatusThatAllowToAddEvidences() {
        List<ObjectiveStatus> objectiveStatusList =
                givenTheStatusWhereYouCanAddEvidences();

        shouldReturnTheExpectedNumberOfStatusThatAllowToAddEvidences(objectiveStatusList);
    }

    @Test
    public void shouldReturnFalseCaseNotAllowedToAddEvidence() {
        ObjectiveStatus status =
                givenAObjectiveStatusInProgress();

        boolean condition =
                whenCheckedIsAllowedToAddEvidence(status);

        shouldReturnFalse(condition);
    }

    private ObjectiveStatus givenAObjectiveStatusInProgress() {
        return ObjectiveStatus.IN_PROGRESS;
    }

    private List<ObjectiveStatus> givenTheStatusWhereYouCanAddEvidences() {
        return ObjectiveStatus.statusWhereYouCanAddEvidences();
    }

    private boolean whenCheckedIsAllowedToAddEvidence(ObjectiveStatus status) {
        return ObjectiveStatus.allowToAddEvidence(status);
    }

    private void shouldReturnTheExpectedNumberOfStatusThatAllowToAddEvidences(List<ObjectiveStatus> objectiveStatusList) {
        assertThat(objectiveStatusList, containsInAnyOrder(ObjectiveStatus.DISCONTINUED, ObjectiveStatus.DONE));
    }

    private void shouldReturnFalse(boolean condition) {
        assertFalse(condition);
    }
}