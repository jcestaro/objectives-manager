package com.github.jcestaro.objectivesmanager.model.service;

import com.github.jcestaro.objectivesmanager.model.entity.Objective;
import com.github.jcestaro.objectivesmanager.model.entity.ObjectiveStatus;
import com.github.jcestaro.objectivesmanager.model.repository.ObjectiveRepository;
import com.github.jcestaro.objectivesmanager.view.form.ObjectiveForm;
import com.github.jcestaro.objectivesmanager.view.viewmodel.ObjectiveView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Service
public class ObjectiveService {

    private ObjectiveRepository repository;

    @Autowired
    public ObjectiveService(ObjectiveRepository repository) {
        this.repository = repository;
    }

    public List<ObjectiveView> find() {
        List<Objective> objectives = repository.findAll();

        return objectives.stream()
            .map(ObjectiveView::new)
            .collect(Collectors.toList());
    }

    public ObjectiveView find(int id) {
        Objective objective = repository.findById(id).get();
        return new ObjectiveView(objective);
    }

    @Transactional
    public ObjectiveView save(ObjectiveForm form) {
        Objective objective = form.formToEntity();
        Objective savedObjective = repository.save(objective);

        return new ObjectiveView(savedObjective);
    }

    @Transactional
    public void update(ObjectiveForm form, int id) {
        Objective objective = repository.findById(id).get();
        Objective updatedObjective = form.updateObjective(objective);
        repository.save(updatedObjective);
    }

    @Transactional
    public void updateStatus(ObjectiveForm form, int id) {
        ObjectiveStatus newStatus = form.getStatus();

        Objective objective = repository.findById(id).get();
        objective.updateStatus(newStatus);

        repository.save(objective);
    }

    @Transactional
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Transactional
    public void deleteInBatch(List<ObjectiveForm> objectivesForm) {
        List<Objective> objectives = objectivesForm.stream()
            .map(ObjectiveForm::formToEntity)
            .collect(Collectors.toList());

        repository.deleteInBatch(objectives);
    }
}
