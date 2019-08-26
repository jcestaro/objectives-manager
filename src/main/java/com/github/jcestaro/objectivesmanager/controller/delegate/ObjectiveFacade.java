package com.github.jcestaro.objectivesmanager.controller.delegate;

import com.github.jcestaro.objectivesmanager.model.entity.Objective;
import com.github.jcestaro.objectivesmanager.model.entity.ObjectiveStatus;
import com.github.jcestaro.objectivesmanager.model.service.ObjectiveService;
import com.github.jcestaro.objectivesmanager.view.form.ObjectiveForm;
import com.github.jcestaro.objectivesmanager.view.viewmodel.ObjectiveView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ObjectiveFacade {

    private ObjectiveService service;

    @Autowired
    public ObjectiveFacade(ObjectiveService service) {
        this.service = service;
    }

    public List<ObjectiveView> find() {
        List<Objective> objectives = service.find();

        return objectives.stream()
            .map(ObjectiveView::new)
            .collect(Collectors.toList());
    }

    public ObjectiveView find(int id) {
        Objective objective = service.find(id).get();
        return new ObjectiveView(objective);
    }

    public ObjectiveView save(ObjectiveForm form) {
        Objective objective = form.toEntity();
        Objective savedObjective = service.save(objective);

        return new ObjectiveView(savedObjective);
    }

    public ObjectiveView save(ObjectiveForm form, int id) {
        Objective objective = service.find(id).get();
        Objective objectiveFormToEntity = form.toEntity();

        objective.addObjective(objectiveFormToEntity);
        service.save(objective);

        return new ObjectiveView(objective);
    }

    public ObjectiveView update(ObjectiveForm form, int id) {
        Objective objective = service.find(id).get();
        Objective objectiveFormToEntity = form.toEntity();

        objective.update(objectiveFormToEntity);
        Objective savedObjective = service.save(objective);

        return new ObjectiveView(savedObjective);
    }

    public ObjectiveView updateStatus(ObjectiveForm form, int id) {
        ObjectiveStatus newStatus = form.getStatus();

        Objective objective = service.find(id).get();
        objective.updateStatus(newStatus);

        Objective savedObjective = service.save(objective);

        return new ObjectiveView(savedObjective);
    }

    public void delete(int id) {
        service.delete(id);
    }

    public void delete(List<ObjectiveForm> objectivesForm) {
        List<Objective> objectives = objectivesForm.stream()
            .map(ObjectiveForm::toEntity)
            .collect(Collectors.toList());

        service.delete(objectives);
    }
}
