package org.app.projectorganizer.repository;

import org.app.projectorganizer.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository extends MongoRepository<Project, Integer>, CustomProjectRepository {
}
