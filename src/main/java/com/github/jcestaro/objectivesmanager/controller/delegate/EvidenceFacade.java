package com.github.jcestaro.objectivesmanager.controller.delegate;

import com.github.jcestaro.objectivesmanager.exception.CannotSaveEvidenceException;
import com.github.jcestaro.objectivesmanager.exception.ObjectiveNotFoundException;
import com.github.jcestaro.objectivesmanager.model.entity.Evidence;
import com.github.jcestaro.objectivesmanager.model.entity.Objective;
import com.github.jcestaro.objectivesmanager.model.service.ObjectiveService;
import com.github.jcestaro.objectivesmanager.view.viewmodel.EvidenceView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class EvidenceFacade {

    private static final String ERROR_MESSAGE_ADD_EVIDENCE = "Was'nt possible to add your evidences!";
    private static final String USER_HOME = "user.home";
    private String directoryPath;

    private ObjectiveService objectiveService;

    @Autowired
    public EvidenceFacade(ObjectiveService objectiveService, @Value("${directory.path}") String directoryPath) {
        this.directoryPath = directoryPath;
        this.objectiveService = objectiveService;
    }

    public List<EvidenceView> find(int id) {
        return objectiveService.find(id)
                .orElseThrow(ObjectiveNotFoundException::new)
                .getEvidences()
                .stream()
                .map(EvidenceView::new)
                .collect(Collectors.toList());
    }

    public List<EvidenceView> save(@NotNull @NotEmpty List<MultipartFile> archives, int id) throws IOException {
        File folder = new File(System.getProperty(USER_HOME) + directoryPath);

        Files.createDirectories(folder.toPath());

        Objective objective = objectiveService.find(id)
                .orElseThrow(ObjectiveNotFoundException::new);

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

        return objectiveService.save(objective).getEvidences()
                .stream()
                .map(EvidenceView::new)
                .collect(Collectors.toList());
    }
}
