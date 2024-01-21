package org.app.projectorganizer.repository;

import org.app.projectorganizer.model.*;

public interface CustomProjectRepository {

    Project getProject(String projectName);
    void deleteProject(String projectName);
    void pushEpic(String projectName, Epic newEpic);
    Epic getEpic(String projectName, String epicName);
    void pullEpic(String projectName, String epicName);
    void pushFeature(String projectName, String epicName, Feature newFeature);
    Feature getFeature(String projectName, String epicName, String featureName);
    void pullFeature(String projectName, String epicName, String featureName);
    void pushUserStory(String projectName, String epicName, String featureName, UserStory newUserStory);
    UserStory getUserStory(String projectName, String epicName, String featureName, String userStoryName);
    void pullUserStory(String projectName, String epicName, String featureName, String userStoryName);
    void pushTask(String projectName, String epicName, String featureName, String userStoryName, Task newTask);
    Task getTask(String projectName, String epicName, String featureName, String userStoryName, String taskName);
    void pullTask(String projectName, String epicName, String featureName, String userStoryName, String taskName);
}
