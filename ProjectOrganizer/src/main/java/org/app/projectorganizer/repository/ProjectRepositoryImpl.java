package org.app.projectorganizer.repository;

import com.mongodb.BasicDBObject;
import org.app.projectorganizer.model.*;
import org.app.projectorganizer.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class ProjectRepositoryImpl implements CustomProjectRepository{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Project getProject(String projectName) {
        return mongoTemplate.findOne(Query.query(Criteria.where("name").is(projectName)), Project.class);
    }

    @Override
    public void deleteProject(String projectName) {
        Project project = getProject(projectName);

        mongoTemplate.remove(project);
    }

    @Override
    public void pushEpic(String projectName, Epic newEpic) {
        mongoTemplate.updateFirst(
                Query.query(
                        Criteria.where("name").is(projectName)
                ),
                new Update().push("epics", newEpic),
                Project.class
        );
    }

    @Override
    public Epic getEpic(String projectName, String epicName) {
        Project project = getProject(projectName);

        int epicIndex = ProjectService.findEpicIndexByName(project, epicName);

        return project.getEpics().get(epicIndex);
    }

    @Override
    public void pullEpic(String projectName, String epicName) {
        mongoTemplate.updateFirst(
                Query.query(
                        Criteria.where("name").is(projectName)
                ),
                new Update().pull("epics", new BasicDBObject("name", epicName)),
                Project.class
        );
    }

    @Override
    public void pushFeature(String projectName, String epicName, Feature newFeature) {
        Project project = getProject(projectName);

        int epicIndex = ProjectService.findEpicIndexByName(project, epicName);

        mongoTemplate.updateFirst(
                Query.query(
                        Criteria.where("name").is(projectName)
                ),
                new Update()
                        .push("epics.%d.features"
                                        .formatted(epicIndex),
                                newFeature),
                Project.class
        );
    }

    @Override
    public Feature getFeature(String projectName, String epicName, String featureName) {
        Project project = getProject(projectName);

        int epicIndex = ProjectService.findEpicIndexByName(project, epicName);
        int featureIndex = ProjectService.findFeatureIndexByName(project, featureName, epicIndex);

        return project.getEpics().get(epicIndex)
                .getFeatures().get(featureIndex);
    }

    @Override
    public void pullFeature(String projectName, String epicName, String featureName) {
        Project project = getProject(projectName);

        int epicIndex = ProjectService.findEpicIndexByName(project, epicName);

        mongoTemplate.updateFirst(
                Query.query(
                        Criteria.where("name").is(projectName)
                ),
                new Update()
                        .pull("epics.%d.features"
                                        .formatted(epicIndex),
                                new BasicDBObject("name", featureName)),
                Project.class
        );
    }

    @Override
    public void pushUserStory(String projectName, String epicName, String featureName, UserStory newUserStory) {
        Project project = getProject(projectName);

        int epicIndex = ProjectService.findEpicIndexByName(project, epicName);
        int featureIndex = ProjectService.findFeatureIndexByName(project, featureName, epicIndex);

        if(epicIndex == -1 || featureIndex == -1) {
            return;
        }

        mongoTemplate.updateFirst(
                Query.query(
                        Criteria.where("name").is(projectName)
                ),
                new Update()
                        .push("epics.%d.features.%d.userStories"
                                        .formatted(epicIndex, featureIndex)
                                , newUserStory),
                Project.class
        );
    }

    @Override
    public UserStory getUserStory(String projectName, String epicName, String featureName, String userStoryName) {
        Project project = getProject(projectName);

        int epicIndex = ProjectService.findEpicIndexByName(project, epicName);
        int featureIndex = ProjectService.findFeatureIndexByName(project, featureName, epicIndex);
        int userStoryIndex = ProjectService.findUserStoryIndexByName(project, userStoryName, epicIndex, featureIndex);

        return project.getEpics().get(epicIndex)
                .getFeatures().get(featureIndex)
                .getUserStories().get(userStoryIndex);
    }

    @Override
    public void pullUserStory(String projectName, String epicName, String featureName, String userStoryName) {
        Project project = getProject(projectName);

        int epicIndex = ProjectService.findEpicIndexByName(project, epicName);
        int featureIndex = ProjectService.findFeatureIndexByName(project, featureName, epicIndex);

        if(epicIndex == -1 || featureIndex == -1) {
            return;
        }

        mongoTemplate.updateFirst(
                Query.query(
                        Criteria.where("name").is(projectName)
                ),
                new Update()
                        .pull("epics.%d.features.%d.userStories"
                                        .formatted(epicIndex, featureIndex),
                                new BasicDBObject("name", userStoryName)),
                Project.class
        );
    }

    @Override
    public void pushTask(String projectName, String epicName, String featureName, String userStoryName, Task newTask) {
        Project project = getProject(projectName);

        int epicIndex = ProjectService.findEpicIndexByName(project, epicName);
        int featureIndex = ProjectService.findFeatureIndexByName(project, featureName, epicIndex);
        int userStoryIndex = ProjectService.findUserStoryIndexByName(project, userStoryName, epicIndex, featureIndex);

        if(epicIndex == -1 || featureIndex == -1 || userStoryIndex == -1) {
            return;
        }

        mongoTemplate.updateFirst(
                Query.query(
                        Criteria.where("name").is(projectName)
                ),
                new Update()
                        .push("epics.%d.features.%d.userStories.%d.tasks"
                                        .formatted(epicIndex, featureIndex, userStoryIndex),
                                newTask),
                Project.class
        );
    }

    @Override
    public Task getTask(String projectName, String epicName, String featureName, String userStoryName, String taskName) {
        Project project = getProject(projectName);

        int epicIndex = ProjectService.findEpicIndexByName(project, epicName);
        int featureIndex = ProjectService.findFeatureIndexByName(project, featureName, epicIndex);
        int userStoryIndex = ProjectService.findUserStoryIndexByName(project, userStoryName, epicIndex, featureIndex);
        int taskIndex = ProjectService.findTaskIndexByName(project, taskName,epicIndex, featureIndex, userStoryIndex);

        return project.getEpics().get(epicIndex)
                .getFeatures().get(featureIndex)
                .getUserStories().get(userStoryIndex)
                .getTasks().get(taskIndex);
    }

    @Override
    public void pullTask(String projectName, String epicName, String featureName, String userStoryName, String taskName) {
        Project project = getProject(projectName);

        int epicIndex = ProjectService.findEpicIndexByName(project, epicName);
        int featureIndex = ProjectService.findFeatureIndexByName(project, featureName, epicIndex);
        int userStoryIndex = ProjectService.findUserStoryIndexByName(project, userStoryName, epicIndex, featureIndex);

        if(epicIndex == -1 || featureIndex == -1 || userStoryIndex == -1) {
            return;
        }

        mongoTemplate.updateFirst(
                Query.query(
                        Criteria.where("name").is(projectName)
                ),
                new Update()
                        .pull("epics.%d.features.%d.userStories.%d.tasks"
                                        .formatted(epicIndex, featureIndex, userStoryIndex),
                                new BasicDBObject("name", taskName)),
                Project.class
        );
    }
}
