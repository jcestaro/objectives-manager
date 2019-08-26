package com.github.jcestaro.objectivesmanager.controller.delegate;

import com.github.jcestaro.objectivesmanager.model.entity.Objective;
import com.github.jcestaro.objectivesmanager.model.service.ObjectiveService;
import com.github.jcestaro.objectivesmanager.view.form.ObjectiveForm;
import com.github.jcestaro.objectivesmanager.view.viewmodel.ObjectiveView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ObjectiveFacadeTest {

    @InjectMocks
    private ObjectiveFacade facade;

    @Mock
    private ObjectiveService service;

    @Test
    public void find() {
        Objective objective =
            givenAObjectiveWithFilledFields();

        ObjectiveView objectiveView =
            whenSearchForTheObjective(objective);

        objectiveAndObjectiveViewShouldHaveTheSameValues(objective, objectiveView);
    }

    @Test
    public void findAll() {
    }

    @Test
    public void save() {
    }

    @Test
    public void saveKeyResult() {
    }

    @Test
    public void update() {
    }

    @Test
    public void updateStatus() {
    }

    @Test
    public void delete() {
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

    private ObjectiveView whenSearchForTheObjective(Objective objective) {
        when(service.find(objective.getId())).thenReturn(Optional.of(objective));

        return facade.find(objective.getId());
    }

    private void objectiveAndObjectiveViewShouldHaveTheSameValues(Objective objective, ObjectiveView objectiveView) {
        assertEquals(objective.getId(), objectiveView.getId());
        assertEquals(objective.getTitle(), objectiveView.getTitle());
        assertEquals(objective.getDescription(), objectiveView.getDescription());
        assertEquals(objective.getCompletionPercentage(), objectiveView.getCompletionPercentage());
        assertEquals(objective.getInvolvementPercentage(), objectiveView.getInvolvementPercentage());
        assertEquals(objective.getUrgencyPercentage(), objectiveView.getUrgencyPercentage());
        assertEquals(objective.getNecessityPercentage(), objectiveView.getNecessityPercentage());
    }
}