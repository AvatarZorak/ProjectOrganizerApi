package org.app.projectorganizer.model;

import java.util.List;

public class UserStory extends TaskTemplate {
    private List<Task> tasks;

    public UserStory(String name, String description, String status, List<Task> tasks) {
        super(name, description, status);
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
