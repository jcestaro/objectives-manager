package com.github.jcestaro.objectivesmanager.view.viewmodel;

import com.github.jcestaro.objectivesmanager.model.entity.Objective;
import com.github.jcestaro.objectivesmanager.model.entity.ObjectiveStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectiveView {

    private int id;
    private String title;
    private String description;

    //Todo fazer com que não devolva todos os objetivos para não carregar um json enorme
    private List<ObjectiveView> keyResults;

    private BigDecimal completionPercentage;
    private BigDecimal involvementPercentage;
    private BigDecimal necessityPercentage;
    private BigDecimal urgencyPercentage;
    private ObjectiveStatus status;
    private BigDecimal priorityPercentage;

    public ObjectiveView(Objective objective) {
        this.title = objective.getTitle();
        this.description = objective.getDescription();
        this.completionPercentage = objective.getCompletionPercentage();
        this.involvementPercentage = objective.getInvolvementPercentage();
        this.necessityPercentage = objective.getNecessityPercentage();
        this.urgencyPercentage = objective.getUrgencyPercentage();
        this.id = objective.getId();
        this.status = objective.getStatus();
        this.priorityPercentage = objective.calculatePriority();

        this.keyResults = objective.getKeyResults()
            .stream()
            .map(ObjectiveView::new)
            .collect(Collectors.toList());
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

    public List<ObjectiveView> getKeyResults() {
        return keyResults;
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

    public BigDecimal getPriorityPercentage() {
        return priorityPercentage;
    }
}