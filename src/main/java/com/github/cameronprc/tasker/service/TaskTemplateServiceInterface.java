package com.github.cameronprc.tasker.service;

import com.github.cameronprc.tasker.model.TaskTemplate;

import java.util.List;
import java.util.Optional;

public interface TaskTemplateServiceInterface {
    List<TaskTemplate> findAll();

    Optional<TaskTemplate> findById(int id);

    TaskTemplate saveTaskTemplate(TaskTemplate taskTemplate);

    void deleteTaskTemplate(TaskTemplate taskTemplate);
}
