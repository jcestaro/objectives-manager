package com.github.jcestaro.objectivesmanager.controller;

import com.github.jcestaro.objectivesmanager.controller.delegate.EvidenceFacade;
import com.github.jcestaro.objectivesmanager.view.viewmodel.EvidenceView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/evidences")
public class EvidenceController {

    private EvidenceFacade facade;

    @Autowired
    public EvidenceController(EvidenceFacade facade) {
        this.facade = facade;
    }

    @PostMapping(path = "/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestPart("archive") List<MultipartFile> archives, @PathVariable int id) throws IOException {
        facade.save(archives, id);
    }

    @GetMapping(path = "/{id}")
    public List<EvidenceView> findById(@PathVariable int id) {
        return facade.find(id);
    }
}
