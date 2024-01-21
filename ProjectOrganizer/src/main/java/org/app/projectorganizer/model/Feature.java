package org.app.projectorganizer.model;

import java.util.List;

public class Feature extends TaskTemplate {

    private List<UserStory> userStories;

    public Feature(String name, String description, String status, List<UserStory> userStories) {
        super(name, description, status);
        this.userStories = userStories;
    }

    public List<UserStory> getUserStories() {
        return userStories;
    }

    public void setUserStories(List<UserStory> userStories) {
        this.userStories = userStories;
    }
}
