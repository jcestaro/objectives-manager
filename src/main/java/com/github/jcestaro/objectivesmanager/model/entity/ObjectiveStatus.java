package com.github.jcestaro.objectivesmanager.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public enum ObjectiveStatus {

    DONE("Done"),
    IN_PROGRESS("In Progress"),
    DISCONTINUED("Discontinued");

    private String description;

    ObjectiveStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static List<ObjectiveStatus> statusWhereYouCanAddEvidences() {
        List<ObjectiveStatus> status = new ArrayList<>();
        status.add(ObjectiveStatus.DISCONTINUED);
        status.add(ObjectiveStatus.DONE);

        return status;
    }

    public static List<String> descriptionOfStatusWhereYouCanAddEvidences() {
        return statusWhereYouCanAddEvidences().stream()
                .map(ObjectiveStatus::getDescription)
                .collect(Collectors.toList());
    }

    public static boolean allowToAddEvidence(ObjectiveStatus status) {
        return statusWhereYouCanAddEvidences().contains(status);
    }
}
