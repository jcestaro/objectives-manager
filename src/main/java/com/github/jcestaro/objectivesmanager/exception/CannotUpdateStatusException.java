package com.github.jcestaro.objectivesmanager.exception;

import com.github.jcestaro.objectivesmanager.model.entity.ObjectiveStatus;

public class CannotUpdateStatusException extends BusinessException {

    private static final String MESSAGE = "Was'nt possible to update your status to %s because there's objectives %s or %s";

    public CannotUpdateStatusException() {
        super(String.format(MESSAGE,
                ObjectiveStatus.DONE.getDescription(),
                ObjectiveStatus.IN_PROGRESS.getDescription(),
                ObjectiveStatus.DISCONTINUED.getDescription())
        );
    }
}
