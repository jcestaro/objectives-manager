package com.github.jcestaro.objectivesmanager.view.form;

import com.github.jcestaro.objectivesmanager.exception.FieldsNotFilledException;
import com.github.jcestaro.objectivesmanager.model.entity.Objective;
import com.github.jcestaro.objectivesmanager.model.entity.ObjectiveStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
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
    private List<ObjectiveForm> objectives;
    private ObjectiveStatus status;

    public Objective formToEntity() {
        validateFields();

        List<Objective> toEntityObjectives = toEntityObjectivesList();

        return new Objective(title,
                             description,
                             toEntityObjectives,
                             completionPercentage,
                             involvementPercentage,
                             necessityPercentage,
                             urgencyPercentage);
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

    private List<Objective> toEntityObjectivesList() {
        if (getObjectives() != null && !getObjectives().isEmpty()) {
            return getObjectives().stream()
                .map(ObjectiveForm::formToEntity)
                .collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    public ObjectiveStatus getStatus() {
        return status;
    }

    private List<ObjectiveForm> getObjectives() {
        return objectives;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
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
