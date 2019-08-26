package com.github.jcestaro.objectivesmanager.model.entity;

import com.github.jcestaro.objectivesmanager.exception.CannotAddEvidenceException;
import com.github.jcestaro.objectivesmanager.exception.CannotUpdateStatusException;
import com.github.jcestaro.objectivesmanager.view.form.ObjectiveForm;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ObjectiveTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldReturnTheExpectedPriority() {
        Objective objective =
            givenAObjectiveWithFilledFields();

        BigDecimal priority =
            whenTheObjectiveCalculatePriority(objective);

        shouldReturnTheExpectedPriority(priority);
    }

    @Test
    public void shouldThrowExceptionCaseCantAddEvidence() {
        expectedException.expect(CannotAddEvidenceException.class);
        expectedException.expectMessage("It is only possible to add evidences in objectives that has the status: [Discontinued, Done], the actual status is: In Progress");

        Objective objective =
            givenAObjectiveWithFilledFields();

        whenAttemptToAddEvidence(objective);
    }

    @Test
    public void shouldAddEvidenceWhenStatusIsDone() {
        Objective objective =
            givenAObjectiveWithFilledFields();

        whenUpdateStatusAndAddEvidence(objective, ObjectiveStatus.DONE);

        shouldAddTheEvidenceToTheObjective(objective);
    }

    @Test
    public void shouldAddEvidenceWhenStatusIsDiscontinued() {
        Objective objective =
            givenAObjectiveWithFilledFields();

        whenUpdateStatusAndAddEvidence(objective, ObjectiveStatus.DISCONTINUED);

        shouldAddTheEvidenceToTheObjective(objective);
    }

    @Test
    public void shouldThrowExceptionCaseAttemptToUpdateObjectiveStatusWithoutUpdateKeyResultsStatusToDone() {
        expectedException.expect(CannotUpdateStatusException.class);
        expectedException.expectMessage("Was'nt possible to update your status to Done because there's objectives In Progress or Discontinued");

        Objective objective =
            givenAObjectiveWithFilledFields();

        whenAddedKeyResultsWithInProgressStatus(objective);

        whenUpdateStatus(objective, ObjectiveStatus.DONE);
    }

    @Test
    public void shouldAllowToUpdateStatusToDoneWhenAllKeyResultsAreDone() {
        Objective objective =
            givenAObjectiveThatContainsKeyResultWithStatusDone();

        whenUpdateStatus(objective, ObjectiveStatus.DONE);

        shouldUpdateTheStatusToDoneWithoutProblem(objective);
    }

    @Test
    public void shouldAllowToUpdateStatusToDiscontinuedWhenThereIsKeyResults() {
        Objective objective =
            givenAObjectiveThatContainsKeyResultWithStatusDone();

        whenUpdateStatus(objective, ObjectiveStatus.DISCONTINUED);

        shouldUpdateTheStatusToDiscontinuedWithoutProblem(objective);
    }

    @Test
    public void shouldAllowToUpdateStatusToDiscontinued() {
        Objective objective =
            givenAObjectiveWithFilledFields();

        whenUpdateStatus(objective, ObjectiveStatus.DISCONTINUED);

        shouldUpdateTheStatusToDiscontinuedWithoutProblem(objective);
    }

    private Objective givenAObjectiveWithFilledFields() {
        ObjectiveForm form = new ObjectiveForm();
        form.setCompletionPercentage(new BigDecimal(0.5));
        form.setInvolvementPercentage(new BigDecimal(0.5));
        form.setNecessityPercentage(new BigDecimal(0.5));
        form.setUrgencyPercentage(new BigDecimal(0.5));
        form.setDescription("Test");
        form.setTitle("Test");

        return form.toEntity();
    }

    private Objective givenAObjectiveThatContainsKeyResultWithStatusDone() {
        Objective objective = givenAObjectiveWithFilledFields();

        Objective objectiveWithStatusDone = givenAObjectiveWithFilledFields();
        objectiveWithStatusDone.updateStatus(ObjectiveStatus.DONE);

        objective.addObjective(objectiveWithStatusDone);
        return objective;
    }

    private void whenUpdateStatus(Objective objective, ObjectiveStatus status) {
        objective.updateStatus(status);
    }

    private void whenAddedKeyResultsWithInProgressStatus(Objective objective) {
        objective.addObjective(objective);
    }

    private void whenUpdateStatusAndAddEvidence(Objective objective, ObjectiveStatus status) {
        whenUpdateStatus(objective, status);
        whenAttemptToAddEvidence(objective);
    }

    private void whenAttemptToAddEvidence(Objective objective) {
        Evidence evidence = new Evidence(objective, "/images");
        objective.addEvidence(evidence);
    }

    private BigDecimal whenTheObjectiveCalculatePriority(Objective objective) {
        return objective.calculatePriority();
    }

    private void shouldUpdateTheStatusToDoneWithoutProblem(Objective objective) {
        shouldUpdateTheStatus(ObjectiveStatus.DONE, objective);
    }

    private void shouldUpdateTheStatusToDiscontinuedWithoutProblem(Objective objective) {
        shouldUpdateTheStatus(ObjectiveStatus.DISCONTINUED, objective);
    }

    private void shouldUpdateTheStatus(ObjectiveStatus expectedStatus, Objective expectedObjective) {
        assertEquals(expectedStatus, expectedObjective.getStatus());
    }

    private void shouldAddTheEvidenceToTheObjective(Objective objective) {
        assertEquals(1, objective.getEvidences().size());
    }

    private void shouldReturnTheExpectedPriority(BigDecimal priority) {
        BigDecimal expectedValue = new BigDecimal(0.50).setScale(2);
        assertEquals(expectedValue, priority);
    }
}