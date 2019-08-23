package com.github.jcestaro.objectivesmanager.exception;

public class ObjectiveNotFoundException extends BusinessException {

    private static final String MESSAGE = "No objectives found!";

    public ObjectiveNotFoundException() {
        super(MESSAGE);
    }
}
