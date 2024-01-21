package org.app.projectorganizer.service;

import org.app.projectorganizer.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    public static int findEpicIndexByName(Project project, String epicName) {
        List<Epic> epics = project.getEpics();

        return findElementByNameInList(epics, epicName);
    }

    public static int findFeatureIndexByName(Project project, String featureName, int epicIndex) {
        List<Feature> features = project.getEpics().get(epicIndex).getFeatures();

        return findElementByNameInList(features, featureName);
    }

    public static int findUserStoryIndexByName(Project project, String userStoryName, int epicIndex, int featureIndex) {
        List<UserStory> userStories = project.getEpics().get(epicIndex).getFeatures().get(featureIndex).getUserStories();

        return findElementByNameInList(userStories, userStoryName);
    }

    public static int findTaskIndexByName(Project project, String taskName, int epicIndex, int featureIndex, int userStoryIndex) {
        List<Task> tasks = project.getEpics().get(epicIndex).getFeatures().get(featureIndex).getUserStories().get(userStoryIndex).getTasks();

        return findElementByNameInList(tasks, taskName);
    }

    private static <T extends TaskTemplate> int findElementByNameInList(List<T> list, String name) {
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).getName().equals(name)) {
                return i;
            }
        }

        return -1;
    }
}
