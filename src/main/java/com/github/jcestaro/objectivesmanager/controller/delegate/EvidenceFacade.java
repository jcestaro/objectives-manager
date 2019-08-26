package com.github.jcestaro.objectivesmanager.controller.delegate;

import com.github.jcestaro.objectivesmanager.exception.CannotSaveEvidenceException;
import com.github.jcestaro.objectivesmanager.model.entity.Evidence;
import com.github.jcestaro.objectivesmanager.model.entity.Objective;
import com.github.jcestaro.objectivesmanager.model.service.ObjectiveService;
import com.github.jcestaro.objectivesmanager.view.viewmodel.EvidenceView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Component
public class EvidenceFacade {

    private static final String ERROR_MESSAGE_ADD_EVIDENCE = "Was'nt possible to add your evidences!";
    private String directoryPath;

    private ObjectiveService objectiveService;

    @Autowired
    public EvidenceFacade(ObjectiveService objectiveService, @Value("${directory.path}") String directoryPath) {
        this.directoryPath = directoryPath;
        this.objectiveService = objectiveService;
    }

    public List<EvidenceView> find(int id) {
        Objective objective = objectiveService.find(id).get();

        return objective.getEvidences()
            .stream()
            .map(EvidenceView::new)
            .collect(Collectors.toList());
    }

    @Transactional
    public List<EvidenceView> save(List<MultipartFile> archives, int id) throws IOException {
        File folder = new File(System.getProperty("user.home") + directoryPath);

        Files.createDirectories(folder.toPath());

        Objective objective = objectiveService.find(id).get();

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

        Objective savedObjective = objectiveService.save(objective);

        return savedObjective.getEvidences()
            .stream()
            .map(EvidenceView::new)
            .collect(Collectors.toList());
    }
}
