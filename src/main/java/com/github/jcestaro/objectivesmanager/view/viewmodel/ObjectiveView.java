package com.github.jcestaro.objectivesmanager.view.viewmodel;

import com.github.jcestaro.objectivesmanager.model.entity.Objective;
import com.github.jcestaro.objectivesmanager.model.entity.ObjectiveStatus;

import java.math.BigDecimal;
import java.util.List;

public class ObjectiveView {

    private int id;
    private String title;
    private String description;
    private List<Objective> objectives;
    private BigDecimal completionPercentage;
    private BigDecimal involvementPercentage;
    private BigDecimal necessityPercentage;
    private BigDecimal urgencyPercentage;
    private ObjectiveStatus status;
    private BigDecimal priorityPercentage;

    @Deprecated
    public ObjectiveView() {
    }

    public ObjectiveView(Objective objective) {
        this.title = objective.getTitle();
        this.description = objective.getDescription();
        this.objectives = objective.getObjectives();
        this.completionPercentage = objective.getCompletionPercentage();
        this.involvementPercentage = objective.getInvolvementPercentage();
        this.necessityPercentage = objective.getNecessityPercentage();
        this.urgencyPercentage = objective.getUrgencyPercentage();
        this.id = objective.getId();
        this.status = objective.getStatus();
        this.priorityPercentage = objective.calculatePriority();
    }

    public BigDecimal getPriorityPercentage() {
        return priorityPercentage;
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

    public ObjectiveStatus getStatus() {
        return status;
    }
}