package com.github.jcestaro.objectivesmanager.controller.delegate;

import com.github.jcestaro.objectivesmanager.model.service.EvidenceService;
import com.github.jcestaro.objectivesmanager.view.viewmodel.EvidenceView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
public class EvidenceFacade {

    private EvidenceService service;

    @Autowired
    public EvidenceFacade(EvidenceService service) {
        this.service = service;
    }

    public void save(List<MultipartFile> archives, int id) throws IOException {
        service.save(archives, id);
    }

    public List<EvidenceView> find(int id) {
        // TODO achar um jeito de carregar a imagem salva na máquina através do caminho dela salvo na Evidência

        return service.find(id);
    }
}
