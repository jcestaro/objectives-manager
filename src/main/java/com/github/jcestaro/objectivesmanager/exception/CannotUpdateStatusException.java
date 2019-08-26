package com.github.jcestaro.objectivesmanager.exception;

import com.github.jcestaro.objectivesmanager.model.entity.ObjectiveStatus;

public class CannotUpdateStatusException extends BusinessException {

    public CannotUpdateStatusException() {
        super(String.format("Was'nt possible to update your status to %s because there's objectives %s or %s",
                            ObjectiveStatus.DONE.getDescription(),
                            ObjectiveStatus.IN_PROGRESS.getDescription(),
                            ObjectiveStatus.DISCONTINUED.getDescription())
        );
    }
}
