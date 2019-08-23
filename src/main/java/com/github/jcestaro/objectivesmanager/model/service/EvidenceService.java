package com.github.jcestaro.objectivesmanager.model.service;

import com.github.jcestaro.objectivesmanager.exception.CannotSaveEvidenceException;
import com.github.jcestaro.objectivesmanager.exception.ObjectiveNotFoundException;
import com.github.jcestaro.objectivesmanager.model.entity.Evidence;
import com.github.jcestaro.objectivesmanager.model.entity.Objective;
import com.github.jcestaro.objectivesmanager.model.repository.ObjectiveRepository;
import com.github.jcestaro.objectivesmanager.view.viewmodel.EvidenceView;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Service
public class EvidenceService {

    private static final String ERROR_MESSAGE_ADD_EVIDENCE = "Was'nt possible to add your evidences!";
    private String directoryPath;

    private ObjectiveRepository objectiveRepository;

    @Autowired
    public EvidenceService(ObjectiveRepository objectiveRepository, @Value("${directory.path}") String directoryPath) {
        this.directoryPath = directoryPath;
        this.objectiveRepository = objectiveRepository;
    }

    public List<EvidenceView> find(int id) {
        Objective objective = objectiveRepository.findById(id).get();

        // TODO achar um jeito de carregar a imagem salva na máquina através do caminho dela salvo na Evidência

        return objective.getEvidences()
            .stream()
            .map(EvidenceView::new)
            .collect(Collectors.toList());
    }

    @Transactional
    public void save(List<MultipartFile> archives, int id) throws IOException {
        File folder = new File(System.getProperty("user.home") + directoryPath);

        Files.createDirectories(folder.toPath());

        Objective objective = objectiveRepository.findById(id).get();

        for (MultipartFile archive : archives) {
            File archiveReceived = new File(folder, Objects.requireNonNull(archive.getOriginalFilename()));

            try (FileOutputStream writer = new FileOutputStream(archiveReceived)) {
                writer.write(archive.getBytes());
                Evidence evidence = new Evidence(objective, archiveReceived.getAbsolutePath());
                objective.addEvidence(evidence);
            } catch (IOException e) {
                throw new CannotSaveEvidenceException(ERROR_MESSAGE_ADD_EVIDENCE, e);
            }
        }

        objectiveRepository.save(objective);
    }
}
