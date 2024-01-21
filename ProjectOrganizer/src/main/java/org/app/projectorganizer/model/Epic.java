package org.app.projectorganizer.model;

import java.util.List;

public class Epic extends TaskTemplate {
    private List<Feature> features;

    public Epic(String name, String description, String status, List<Feature> features) {
        super(name, description, status);
        this.features = features;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }
}
