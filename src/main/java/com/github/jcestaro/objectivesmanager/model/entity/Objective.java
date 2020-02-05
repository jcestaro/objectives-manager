package com.github.jcestaro.objectivesmanager.model.entity;

import com.github.jcestaro.objectivesmanager.exception.CannotAddEvidenceException;
import com.github.jcestaro.objectivesmanager.exception.CannotUpdateStatusException;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
public class Objective {

    private static final BigDecimal NUMBER_OF_FIELDS_FOR_AVERAGE = new BigDecimal(4);
    private static final int SCALE = 2;

    @Id
    @GeneratedValue
    private int id;

    private String title;
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Objective> keyResults;

    @Enumerated(value = EnumType.STRING)
    private ObjectiveStatus status;

    @OneToMany(mappedBy = "objective", cascade = CascadeType.ALL)
    private List<Evidence> evidences;

    private BigDecimal completionPercentage;
    private BigDecimal involvementPercentage;
    private BigDecimal necessityPercentage;
    private BigDecimal urgencyPercentage;

    @Deprecated
    public Objective() {
    }

    public Objective(String title,
                     String description,
                     BigDecimal completionPercentage,
                     BigDecimal involvementPercentage,
                     BigDecimal necessityPercentage,
                     BigDecimal urgencyPercentage) {

        this.title = title;
        this.description = description;
        this.completionPercentage = completionPercentage;
        this.involvementPercentage = involvementPercentage;
        this.necessityPercentage = necessityPercentage;
        this.urgencyPercentage = urgencyPercentage;

        this.status = ObjectiveStatus.IN_PROGRESS;
        this.evidences = new ArrayList<>();
        this.keyResults = new ArrayList<>();
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private void setCompletionPercentage(BigDecimal completionPercentage) {
        this.completionPercentage = completionPercentage;
    }

    private void setInvolvementPercentage(BigDecimal involvementPercentage) {
        this.involvementPercentage = involvementPercentage;
    }

    private void setNecessityPercentage(BigDecimal necessityPercentage) {
        this.necessityPercentage = necessityPercentage;
    }

    private void setUrgencyPercentage(BigDecimal urgencyPercentage) {
        this.urgencyPercentage = urgencyPercentage;
    }

    public List<Evidence> getEvidences() {
        return Collections.unmodifiableList(evidences);
    }

    public ObjectiveStatus getStatus() {
        return status;
    }

    public String getStatusDescription() {
        return status.getDescription();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Objective> getKeyResults() {
        return Collections.unmodifiableList(keyResults);
    }

    public BigDecimal getCompletionPercentage() {
        return completionPercentage;
    }

    public BigDecimal getInvolvementPercentage() {
        return involvementPercentage;
    }

    public BigDecimal getNecessityPercentage() {
        return necessityPercentage;
    }

    public BigDecimal getUrgencyPercentage() {
        return urgencyPercentage;
    }

    public BigDecimal calculatePriority() {
        return getCompletionPercentage()
                .add(getInvolvementPercentage())
                .add(getNecessityPercentage())
                .add(getUrgencyPercentage())
                .divide(NUMBER_OF_FIELDS_FOR_AVERAGE, SCALE, BigDecimal.ROUND_HALF_UP);
    }

    public void addObjective(Objective objective) {
        this.keyResults.add(Objects.requireNonNull(objective));
    }

    public Objective addEvidence(Evidence evidence) {
        if (itsNotPossibleToAddEvidence()) {
            throw new CannotAddEvidenceException(this);
        }
        this.evidences.add(evidence);
        return this;
    }

    public Objective update(Objective formAsEntity) {
        this.setTitle(formAsEntity.getTitle());
        this.setDescription(formAsEntity.getDescription());
        this.setInvolvementPercentage(formAsEntity.getInvolvementPercentage());
        this.setNecessityPercentage(formAsEntity.getNecessityPercentage());
        this.setCompletionPercentage(formAsEntity.getCompletionPercentage());
        this.setUrgencyPercentage(formAsEntity.getUrgencyPercentage());

        return this;
    }

    public void updateStatus(ObjectiveStatus newStatus) {
        if (hasObjectivesUndone() && ObjectiveStatus.DONE.equals(newStatus)) {
            throw new CannotUpdateStatusException();
        }

        this.status = newStatus;
    }

    private boolean isAllObjectivesDone() {
        return getKeyResults().stream()
                .map(Objective::getStatus)
                .allMatch(ObjectiveStatus.DONE::equals);
    }

    private boolean hasObjectivesUndone() {
        return !isAllObjectivesDone();
    }

    private boolean allowToAddEvidence() {
        return ObjectiveStatus.allowToAddEvidence(this.getStatus());
    }

    private boolean itsNotPossibleToAddEvidence() {
        return !allowToAddEvidence();
    }
}
