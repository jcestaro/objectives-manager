package com.github.jcestaro.objectivesmanager.controller.delegate;

import com.github.jcestaro.objectivesmanager.model.entity.Objective;
import com.github.jcestaro.objectivesmanager.model.entity.ObjectiveStatus;
import com.github.jcestaro.objectivesmanager.model.service.ObjectiveService;
import com.github.jcestaro.objectivesmanager.view.form.ObjectiveForm;
import com.github.jcestaro.objectivesmanager.view.viewmodel.ObjectiveView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
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
        List<Objective> objectives =
                givenAListOfObjectives();

        List<ObjectiveView> objectivesViews =
                whenSearchForAllObjectives(objectives);

        bothObjectiveKeyResultsShouldHaveTheSameSize(objectives, objectivesViews);
    }

    @Test
    public void save() {
        ObjectiveForm form =
                givenAValidObjectiveForm();

        Objective objective =
                givenAObjectiveWithFilledFields();

        ObjectiveView objectiveView =
                whenSaveTheObjectiveForm(form, objective);

        objectiveFormAndObjectiveViewShouldHaveTheSameValues(form, objectiveView);
    }

    @Test
    public void saveKeyResult() {
        ObjectiveForm form =
                givenAValidObjectiveForm();

        Objective objective =
                givenAObjectiveWithFilledFields();

        ObjectiveView objectiveView =
                whenSaveFormIntoObjectiveKeyResultList(form, objective);

        objectiveAndObjectiveViewShouldHaveTheSameValues(objective, objectiveView);
    }

    @Test
    public void update() {
        Objective objective =
                givenAObjectiveWithFilledFields();

        ObjectiveForm form =
                givenAValidObjectiveFormWithValuesToUpdate();

        ObjectiveView updatedObjective =
                whenUpdateObjectiveFields(objective, form);

        objectiveFormAndObjectiveViewShouldHaveTheSameValues(form, updatedObjective);
    }

    @Test
    public void updateStatus() {
        Objective objective =
                givenAObjectiveWithFilledFields();

        ObjectiveForm form =
                givenAValidFormWithStatusDone();

        ObjectiveView objectiveView =
                whenUpdateObjectiveStatus(objective, form);

        thenTheObjectiveStatusShouldBeDone(objectiveView);
    }

    @Test
    public void delete() {
        Objective objective =
                givenAObjectiveWithFilledFields();

        ObjectiveForm form =
                givenAValidObjectiveForm();

        whenDeleteTheObjective(objective, form);

        shouldDeleteTheObjective();
    }

    private List<Objective> givenAListOfObjectives() {
        return Arrays.asList(givenAObjectiveWithFilledFields(), givenAObjectiveWithFilledFields());
    }

    private Objective givenAObjectiveWithFilledFields() {
        ObjectiveForm form = givenAValidObjectiveForm();
        return form.toEntity();
    }

    private ObjectiveForm givenAValidObjectiveForm() {
        ObjectiveForm form = new ObjectiveForm();
        form.setCompletionPercentage(new BigDecimal(0.5));
        form.setInvolvementPercentage(new BigDecimal(0.5));
        form.setNecessityPercentage(new BigDecimal(0.5));
        form.setUrgencyPercentage(new BigDecimal(0.5));
        form.setDescription("Test");
        form.setTitle("Test");
        return form;
    }

    private ObjectiveForm givenAValidObjectiveFormWithValuesToUpdate() {
        ObjectiveForm form = new ObjectiveForm();
        form.setCompletionPercentage(new BigDecimal(0.2));
        form.setInvolvementPercentage(new BigDecimal(0.6));
        form.setNecessityPercentage(new BigDecimal(0.9));
        form.setUrgencyPercentage(new BigDecimal(0.4));
        form.setDescription("TestUpdate");
        form.setTitle("TestUpdate");
        return form;
    }

    private ObjectiveForm givenAValidFormWithStatusDone() {
        ObjectiveForm form = givenAValidObjectiveForm();

        form.setStatus(ObjectiveStatus.DONE);

        return form;
    }

    private ObjectiveView whenUpdateObjectiveStatus(Objective objective, ObjectiveForm form) {
        when(service.find(1)).thenReturn(Optional.of(objective));
        when(service.save(any())).thenReturn(objective);

        return facade.updateStatus(form, 1);
    }

    private void whenDeleteTheObjective(Objective objective, ObjectiveForm form) {
        whenSaveTheObjectiveForm(form, objective);
        facade.delete(1);
    }

    private ObjectiveView whenUpdateObjectiveFields(Objective objective, ObjectiveForm form) {
        when(service.find(1)).thenReturn(Optional.of(objective));
        when(service.save(any())).thenReturn(objective);

        return facade.update(form, 1);
    }

    private ObjectiveView whenSaveFormIntoObjectiveKeyResultList(ObjectiveForm form, Objective objective) {
        when(service.find(1)).thenReturn(Optional.of(objective));
        when(service.save(any())).thenReturn(objective);

        return facade.save(form, 1);
    }

    private ObjectiveView whenSearchForTheObjective(Objective objective) {
        when(service.find(1)).thenReturn(Optional.of(objective));

        return facade.find(1);
    }

    private List<ObjectiveView> whenSearchForAllObjectives(List<Objective> objectives) {
        when(service.find()).thenReturn(objectives);

        return facade.find();
    }

    private ObjectiveView whenSaveTheObjectiveForm(ObjectiveForm form, Objective objective) {
        when(service.save(any())).thenReturn(objective);

        return facade.save(form);
    }

    private void bothObjectiveKeyResultsShouldHaveTheSameSize(List<Objective> objectives, List<ObjectiveView> objectivesViews) {
        assertEquals(objectives.size(), objectivesViews.size());
    }

    private void objectiveAndObjectiveViewShouldHaveTheSameValues(Objective objective, ObjectiveView objectiveView) {
        assertEquals(objective.getId(), objectiveView.getId());
        assertEquals(objective.getTitle(), objectiveView.getTitle());
        assertEquals(objective.getDescription(), objectiveView.getDescription());
        assertEquals(objective.getCompletionPercentage(), objectiveView.getCompletionPercentage());
        assertEquals(objective.getInvolvementPercentage(), objectiveView.getInvolvementPercentage());
        assertEquals(objective.getUrgencyPercentage(), objectiveView.getUrgencyPercentage());
        assertEquals(objective.getNecessityPercentage(), objectiveView.getNecessityPercentage());
        bothObjectiveKeyResultsShouldHaveTheSameSize(objective.getKeyResults(), objectiveView.getKeyResults());
    }

    private void objectiveFormAndObjectiveViewShouldHaveTheSameValues(ObjectiveForm form, ObjectiveView objectiveView) {
        assertEquals(form.getTitle(), objectiveView.getTitle());
        assertEquals(form.getDescription(), objectiveView.getDescription());
        assertEquals(form.getCompletionPercentage(), objectiveView.getCompletionPercentage());
        assertEquals(form.getInvolvementPercentage(), objectiveView.getInvolvementPercentage());
        assertEquals(form.getUrgencyPercentage(), objectiveView.getUrgencyPercentage());
        assertEquals(form.getNecessityPercentage(), objectiveView.getNecessityPercentage());
    }

    private void thenTheObjectiveStatusShouldBeDone(ObjectiveView objectiveView) {
        assertEquals(ObjectiveStatus.DONE, objectiveView.getStatus());
    }

    private void shouldDeleteTheObjective() {
        verify(service).delete(1);
    }
}