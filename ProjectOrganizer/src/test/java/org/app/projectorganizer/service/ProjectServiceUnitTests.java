package org.app.projectorganizer.service;

import org.app.projectorganizer.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectServiceUnitTests {

    private Project project = new Project(
            "Project", "This is a project", List.of(
            new Epic(
                    "Epic", "This is an epic", "in_progress",
                    List.of(new Feature(
                            "Feature", "This is a feature", "in_progress",
                            List.of(new UserStory(
                                    "UserStory", "This is a user story", "in_progress",
                                    List.of(new Task(
                                            "Task", "This is a task", "done")))))))));;

    @Test
    public void testFindEpicIndexByName() {
        assertEquals(0, ProjectService.findEpicIndexByName(project, "Epic"));
    }

    @Test
    public void testFindFeatureIndexByName() {
        assertEquals(0, ProjectService.findFeatureIndexByName(project, "Feature", 0));
    }

    @Test
    public void testFindUserStoryIndexByName() {
        assertEquals(0, ProjectService.findUserStoryIndexByName(project, "UserStory", 0, 0));
    }

    @Test
    public void testFindTaskIndexByName() {
        assertEquals(0, ProjectService.findTaskIndexByName(project, "Task", 0, 0, 0));
    }

    @Test
    public void testFindTaskIndexByNameNameError() {
        assertEquals(-1, ProjectService.findTaskIndexByName(project, "TaskExample", 0, 0, 0));
    }

//    @Test
//    public void testFindTaskIndexByNameIndexError() {
//        assertEquals(-1, ProjectService.findTaskIndexByName(project, "Task", 1, 0, 0));
//    }
}
