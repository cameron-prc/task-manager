package com.github.cameronprc.tasker.service;

import com.github.cameronprc.tasker.model.TaskScheduler;
import com.github.cameronprc.tasker.model.TaskTemplate;
import com.github.cameronprc.tasker.repository.TaskSchedulerRepository;
import com.github.cameronprc.tasker.repository.TaskTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskSchedulerService {

    @Autowired
    private TaskSchedulerRepository taskSchedulerRepository;

    public List<TaskScheduler> findAll() {
        return taskSchedulerRepository.findAll();
    }

    public Optional<TaskScheduler> findById(int id) {
        return taskSchedulerRepository.findById(id);
    }

    public TaskScheduler saveTaskScheduler(TaskScheduler taskScheduler) {
        return taskSchedulerRepository.save(taskScheduler);
    }

    public void deleteTaskScheduler(TaskScheduler taskScheduler) {
        taskSchedulerRepository.delete(taskScheduler);
    }
}
