package com.github.jcestaro.objectivesmanager.controller;

import com.github.jcestaro.objectivesmanager.model.service.ObjectiveService;
import com.github.jcestaro.objectivesmanager.view.form.ObjectiveForm;
import com.github.jcestaro.objectivesmanager.view.viewmodel.ObjectiveView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/objectives")
public class ObjectiveController {

    private ObjectiveService service;

    @Autowired
    public ObjectiveController(ObjectiveService service) {
        this.service = service;
    }

    @GetMapping
    public List<ObjectiveView> findAll() {
        return service.find();
    }

    @GetMapping(path = "/{id}")
    public ObjectiveView findById(@PathVariable int id) {
        return service.find(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ObjectiveView save(@RequestBody @Valid ObjectiveForm form) {
        return service.save(form);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@RequestBody @Valid ObjectiveForm form, @PathVariable int id) {
        service.update(form, id);
    }

    @PatchMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateStatus(@RequestBody ObjectiveForm form, @PathVariable int id) {
        service.updateStatus(form, id);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    @DeleteMapping
    public void deleteInBatch(@RequestBody List<ObjectiveForm> form) {
        service.deleteInBatch(form);
    }
}
