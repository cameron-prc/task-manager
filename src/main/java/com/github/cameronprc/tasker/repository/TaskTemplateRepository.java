package com.github.cameronprc.tasker.repository;

import com.github.cameronprc.tasker.model.TaskTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskTemplateRepository extends JpaRepository<TaskTemplate, Integer> {
}
