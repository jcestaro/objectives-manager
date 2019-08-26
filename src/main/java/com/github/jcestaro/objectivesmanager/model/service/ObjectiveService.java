package com.github.jcestaro.objectivesmanager.model.service;

import com.github.jcestaro.objectivesmanager.model.entity.Objective;
import com.github.jcestaro.objectivesmanager.model.repository.ObjectiveRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
public class ObjectiveService {

    private ObjectiveRepository repository;

    @Autowired
    public ObjectiveService(ObjectiveRepository repository) {
        this.repository = repository;
    }

    public List<Objective> find() {
        return repository.findAll();
    }

    public Optional<Objective> find(int id) {
        return repository.findById(id);
    }

    @Transactional
    public Objective save(Objective objective) {
        return repository.save(objective);
    }

    @Transactional
    public void delete(int id) {
        repository.deleteById(id);
    }
}
