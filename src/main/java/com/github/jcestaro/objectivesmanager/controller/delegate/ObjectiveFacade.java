package com.github.jcestaro.objectivesmanager.controller.delegate;

import com.github.jcestaro.objectivesmanager.exception.ObjectiveNotFoundException;
import com.github.jcestaro.objectivesmanager.model.entity.Objective;
import com.github.jcestaro.objectivesmanager.model.entity.ObjectiveStatus;
import com.github.jcestaro.objectivesmanager.model.service.ObjectiveService;
import com.github.jcestaro.objectivesmanager.view.form.ObjectiveForm;
import com.github.jcestaro.objectivesmanager.view.viewmodel.ObjectiveView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

@Component
public class ObjectiveFacade {

    private ObjectiveService service;

    @Autowired
    public ObjectiveFacade(ObjectiveService service) {
        this.service = service;
    }

    public List<ObjectiveView> find() {
        return service.find().stream()
            .map(ObjectiveView::new)
            .collect(Collectors.toList());
    }

    public ObjectiveView find(int id) {
        Objective objective = service.find(id)
            .orElseThrow(ObjectiveNotFoundException::new);

        return new ObjectiveView(objective);
    }

    public ObjectiveView save(@NotNull ObjectiveForm form) {
        Objective objective = form.toEntity();
        Objective savedObjective = service.save(objective);

        return new ObjectiveView(savedObjective);
    }

    public ObjectiveView save(@NotNull ObjectiveForm form, int id) {
        Objective objectiveFormToEntity = form.toEntity();

        Objective objective = service.find(id)
            .orElseThrow(ObjectiveNotFoundException::new);

        objective.addObjective(objectiveFormToEntity);
        service.save(objective);

        return new ObjectiveView(objective);
    }

    public ObjectiveView update(@NotNull ObjectiveForm form, int id) {
        Objective objectiveFormToEntity = form.toEntity();

        Objective objective = service.find(id)
            .orElseThrow(ObjectiveNotFoundException::new);

        objective.update(objectiveFormToEntity);
        Objective savedObjective = service.save(objective);

        return new ObjectiveView(savedObjective);
    }

    public ObjectiveView updateStatus(@NotNull ObjectiveForm form, int id) {
        ObjectiveStatus newStatus = form.getStatus();

        Objective objective = service.find(id)
            .orElseThrow(ObjectiveNotFoundException::new);

        objective.updateStatus(newStatus);

        Objective savedObjective = service.save(objective);

        return new ObjectiveView(savedObjective);
    }

    public void delete(int id) {
        service.delete(id);
    }
}
