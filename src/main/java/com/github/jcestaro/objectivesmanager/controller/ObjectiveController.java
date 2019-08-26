package com.github.jcestaro.objectivesmanager.controller;

import com.github.jcestaro.objectivesmanager.controller.delegate.ObjectiveFacade;
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

    private ObjectiveFacade facade;

    @Autowired
    public ObjectiveController(ObjectiveFacade facade) {
        this.facade = facade;
    }

    @GetMapping
    public List<ObjectiveView> findAll() {
        return facade.find();
    }

    @GetMapping(path = "/{id}")
    public ObjectiveView findById(@PathVariable int id) {
        return facade.find(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ObjectiveView save(@RequestBody @Valid ObjectiveForm form) {
        return facade.save(form);
    }

    @PostMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ObjectiveView saveKeyResult(@RequestBody @Valid ObjectiveForm form, @PathVariable int id) {
        return facade.save(form, id);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ObjectiveView update(@RequestBody @Valid ObjectiveForm form, @PathVariable int id) {
        return facade.update(form, id);
    }

    @PatchMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ObjectiveView updateStatus(@RequestBody ObjectiveForm form, @PathVariable int id) {
        return facade.updateStatus(form, id);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable int id) {
        facade.delete(id);
    }

    @DeleteMapping
    public void delete(@RequestBody List<ObjectiveForm> form) {
        facade.delete(form);
    }
}
