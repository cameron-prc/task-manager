package com.github.cameronprc.tasker.service;

import com.github.cameronprc.tasker.model.TaskTemplate;
import com.github.cameronprc.tasker.repository.TaskTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskTemplateService implements TaskTemplateServiceInterface {

    @Autowired
    private TaskTemplateRepository taskTemplateRepository;

    @Override
    public List<TaskTemplate> findAll() {
        return taskTemplateRepository.findAll();
    }

    @Override
    public Optional<TaskTemplate> findById(int id) {
        return taskTemplateRepository.findById(id);
    }

    @Override
    public TaskTemplate saveTaskTemplate(TaskTemplate taskTemplate) {
        return taskTemplateRepository.save(taskTemplate);
    }

    @Override
    public void deleteTaskTemplate(TaskTemplate taskTemplate) {
        taskTemplateRepository.delete(taskTemplate);
    }
}
