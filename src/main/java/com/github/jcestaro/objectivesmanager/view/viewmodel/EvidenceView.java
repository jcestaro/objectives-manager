package com.github.jcestaro.objectivesmanager.view.viewmodel;

import com.github.jcestaro.objectivesmanager.model.entity.Evidence;

public class EvidenceView {

    private int id;
    private String imagePath;

    public EvidenceView(Evidence evidence) {
        this.id = evidence.getId();
        this.imagePath = evidence.getImagePath();
    }

    public int getId() {
        return id;
    }

    public String getImagePath() {
        return imagePath;
    }
}
