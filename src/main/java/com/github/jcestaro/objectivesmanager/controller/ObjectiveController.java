package com.github.jcestaro.objectivesmanager.controller;

import com.github.jcestaro.objectivesmanager.controller.delegate.ObjectiveFacade;
import com.github.jcestaro.objectivesmanager.view.form.ObjectiveForm;
import com.github.jcestaro.objectivesmanager.view.viewmodel.ObjectiveView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/{id}")
    public ObjectiveView findById(@PathVariable int id) {
        return facade.find(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ObjectiveView save(@RequestBody @Valid ObjectiveForm form) {
        return facade.save(form);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ObjectiveView saveKeyResult(@RequestBody @Valid ObjectiveForm form, @PathVariable int id) {
        return facade.save(form, id);
    }

    @PutMapping("/{id}")
    public ObjectiveView update(@RequestBody @Valid ObjectiveForm form, @PathVariable int id) {
        return facade.update(form, id);
    }

    @PatchMapping("/{id}")
    public ObjectiveView updateStatus(@RequestBody ObjectiveForm form, @PathVariable int id) {
        return facade.updateStatus(form, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        facade.delete(id);
    }
}
