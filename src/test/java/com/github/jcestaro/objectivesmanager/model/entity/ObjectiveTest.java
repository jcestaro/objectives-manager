package com.github.jcestaro.objectivesmanager.model.entity;

import com.github.jcestaro.objectivesmanager.view.form.ObjectiveForm;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ObjectiveTest {

    @Test
    public void shouldCalculateThePriorityAsExpected() {
        Objective objective =
            givenAObjectiveWithFilledFields();

        BigDecimal priority =
            whenTheObjectiveCalculatePriority(objective);

        shouldReturnTheExpectedPriority(priority);
    }

    @Test
    public void addEvidence() {

    }

    @Test
    public void update() {

    }

    @Test
    public void updateStatus() {

    }

    private void shouldReturnTheExpectedPriority(BigDecimal priority) {
        BigDecimal expectedValue = new BigDecimal(0.50).setScale(2);
        assertEquals(expectedValue, priority);
    }

    private BigDecimal whenTheObjectiveCalculatePriority(Objective objective) {
        return objective.calculatePriority();
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
}