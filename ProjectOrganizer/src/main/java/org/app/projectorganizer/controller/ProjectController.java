package org.app.projectorganizer.controller;

import org.app.projectorganizer.model.*;
import org.app.projectorganizer.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    private ProjectRepository repo;

    @GetMapping("/projects")
    public List<Project> getAllProjects() {
        return repo.findAll();
    }

    @PostMapping("/project")
    public List<Project> addProject(@RequestBody Project newProject) {
        repo.save(newProject);

        return repo.findAll();
    }

    @DeleteMapping("/project={projectName}")
    public List<Project> deleteProject(@PathVariable String projectName) {
        repo.deleteProject(projectName);

        return repo.findAll();
    }

    @GetMapping("/project={projectName}")
    public Project getProject(@PathVariable String projectName) {
        return repo.getProject(projectName);
    }

    @PostMapping("/project={projectName}/epic")
    public List<Project> addEpic(@RequestBody Epic newEpic, @PathVariable String projectName) {
        repo.pushEpic(projectName, newEpic);

        return repo.findAll();
    }

    @GetMapping("/project={projectName}/epic={epicName}")
    public Epic getEpic(@PathVariable String projectName, @PathVariable String epicName) {
        return repo.getEpic(projectName, epicName);
    }

    @DeleteMapping("/project={projectName}/epic={epicName}")
    public List<Project> deleteEpic(@PathVariable String projectName, @PathVariable String epicName) {
        repo.pullEpic(projectName, epicName);

        return repo.findAll();
    }

    @PostMapping("/project={projectName}/epic={epicName}/feature")
    public List<Project> addFeature(@PathVariable String projectName, @PathVariable String epicName, @RequestBody Feature newFeature) {
        repo.pushFeature(projectName, epicName, newFeature);

        return repo.findAll();
    }

    @GetMapping("/project={projectName}/epic={epicName}/feature={featureName}")
    public Feature getFeature(@PathVariable String projectName, @PathVariable String epicName, @PathVariable String featureName) {
        return repo.getFeature(projectName, epicName, featureName);
    }

    @DeleteMapping("/project={projectName}/epic={epicName}/feature={featureName}")
    public List<Project> deleteFeature(@PathVariable String projectName, @PathVariable String epicName, @PathVariable String featureName) {
        repo.pullFeature(projectName, epicName, featureName);

        return repo.findAll();
    }

    @PostMapping("/project={projectName}/epic={epicName}/feature={featureName}/userStory")
    public List<Project> addUserStory(@PathVariable String projectName, @PathVariable String epicName, @PathVariable String featureName, @RequestBody UserStory newUserStory) {
        repo.pushUserStory(projectName, epicName, featureName, newUserStory);

        return repo.findAll();
    }

    @GetMapping("/project={projectName}/epic={epicName}/feature={featureName}/userStory={userStoryName}")
    public UserStory getUserStory(@PathVariable String projectName, @PathVariable String epicName, @PathVariable String featureName, @PathVariable String userStoryName) {
        return repo.getUserStory(projectName, epicName, featureName, userStoryName);
    }

    @DeleteMapping("/project={projectName}/epic={epicName}/feature={featureName}/userStory={userStoryName}")
    public List<Project> deleteUserStory(@PathVariable String projectName, @PathVariable String epicName, @PathVariable String featureName, @PathVariable String userStoryName) {
        repo.pullUserStory(projectName, epicName, featureName, userStoryName);

        return repo.findAll();
    }

    @PostMapping("/project={projectName}/epic={epicName}/feature={featureName}/userStory={userStoryName}/task")
    public List<Project> addTask(@PathVariable String projectName, @PathVariable String epicName, @PathVariable String featureName, @PathVariable String userStoryName, @RequestBody Task newTask) {
        repo.pushTask(projectName, epicName, featureName, userStoryName, newTask);

        return repo.findAll();
    }

    @GetMapping("/project={projectName}/epic={epicName}/feature={featureName}/userStory={userStoryName}/task={taskName}")
    public Task getTask(@PathVariable String projectName, @PathVariable String epicName, @PathVariable String featureName, @PathVariable String userStoryName, @PathVariable String taskName) {
        return repo.getTask(projectName, epicName, featureName, userStoryName, taskName);
    }

    @DeleteMapping("/project={projectName}/epic={epicName}/feature={featureName}/userStory={userStoryName}/task={taskName}")
    public List<Project> deleteTask(@PathVariable String projectName, @PathVariable String epicName, @PathVariable String featureName, @PathVariable String userStoryName, @PathVariable String taskName) {
        repo.pullTask(projectName, epicName, featureName, userStoryName, taskName);

        return repo.findAll();
    }
}
