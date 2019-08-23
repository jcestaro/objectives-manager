package com.github.jcestaro.objectivesmanager.exception;

public class FieldsNotFilledException extends BusinessException {

    private static final String MESSAGE = "The required fields has'nt been filled!";

    public FieldsNotFilledException() {
        super(MESSAGE);
    }
}
