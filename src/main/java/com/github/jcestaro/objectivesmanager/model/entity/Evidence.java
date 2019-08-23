package com.github.jcestaro.objectivesmanager.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Evidence {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "objective_id")
    private Objective objective;

    private String imagePath;

    @Deprecated
    public Evidence() {
    }

    public Evidence(Objective objective, String imagePath) {
        this.objective = objective;
        this.imagePath = imagePath;
    }

    public Objective getObjective() {
        return objective;
    }

    public int getId() {
        return id;
    }

    public String getImagePath() {
        return imagePath;
    }
}
