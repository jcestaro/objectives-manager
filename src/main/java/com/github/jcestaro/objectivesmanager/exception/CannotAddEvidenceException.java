package com.github.jcestaro.objectivesmanager.exception;

import com.github.jcestaro.objectivesmanager.model.entity.Objective;
import com.github.jcestaro.objectivesmanager.model.entity.ObjectiveStatus;

import java.text.MessageFormat;

public class CannotAddEvidenceException extends BusinessException {

    private static final String MESSAGE = "It is only possible to add evidences in objectives that has the status: {0}, the actual status is: {1}";

    public CannotAddEvidenceException(Objective objective) {
        super(MessageFormat.format(MESSAGE, ObjectiveStatus.descriptionOfStatusWhereYouCanAddEvidences(), objective.getStatusDescription()));
    }
}
