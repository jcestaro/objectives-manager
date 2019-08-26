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
    private ObjectiveStatus status;

    public Objective toEntity() {
        validateFields();

        return new Objective(title,
                             description,
                             completionPercentage,
                             involvementPercentage,
                             necessityPercentage,
                             urgencyPercentage);
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCompletionPercentage(BigDecimal completionPercentage) {
        this.completionPercentage = completionPercentage;
    }

    public void setInvolvementPercentage(BigDecimal involvementPercentage) {
        this.involvementPercentage = involvementPercentage;
    }

    public void setNecessityPercentage(BigDecimal necessityPercentage) {
        this.necessityPercentage = necessityPercentage;
    }

    public void setUrgencyPercentage(BigDecimal urgencyPercentage) {
        this.urgencyPercentage = urgencyPercentage;
    }

    public void setStatus(ObjectiveStatus status) {
        this.status = status;
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
}
