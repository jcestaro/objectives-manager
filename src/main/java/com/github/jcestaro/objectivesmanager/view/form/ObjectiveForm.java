package com.github.jcestaro.objectivesmanager.view.form;

import com.github.jcestaro.objectivesmanager.exception.FieldsNotFilledException;
import com.github.jcestaro.objectivesmanager.model.entity.Objective;
import com.github.jcestaro.objectivesmanager.model.entity.ObjectiveStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ObjectiveForm {

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private String description;

    @NotNull
    private BigDecimal completionPercentage;

    @NotNull
    private BigDecimal involvementPercentage;

    @NotNull
    private BigDecimal necessityPercentage;

    @NotNull
    private BigDecimal urgencyPercentage;

    private List<Objective> objectives;
    private ObjectiveStatus status;

    public Objective formToEntity() {
        validateFields();
        return new Objective(getTitle(),
                             getDescription(),
                             getObjectives(),
                             getCompletionPercentage(),
                             getInvolvementPercentage(),
                             getNecessityPercentage(),
                             getUrgencyPercentage());
    }

    public Objective updateObjective(Objective objective) {
        validateFields();

        objective.setTitle(this.getTitle());
        objective.setDescription(this.getDescription());
        objective.setObjectives(this.getObjectives());
        objective.setInvolvementPercentage(this.getInvolvementPercentage());
        objective.setNecessityPercentage(this.getNecessityPercentage());
        objective.setCompletionPercentage(this.getCompletionPercentage());
        objective.setUrgencyPercentage(this.getUrgencyPercentage());

        return objective;
    }

    private void validateFields() {
        boolean allFieldsFilled = Stream.of(title,
                                            description,
                                            completionPercentage,
                                            involvementPercentage,
                                            necessityPercentage,
                                            urgencyPercentage)
            .allMatch(Objects::nonNull);

        if (!allFieldsFilled) {
            throw new FieldsNotFilledException();
        }
    }

    public ObjectiveStatus getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Objective> getObjectives() {
        return objectives;
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
}
