package com.github.cameronprc.tasker.service;

import com.github.cameronprc.tasker.model.Task;
import com.github.cameronprc.tasker.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("taskService")
public class TaskService implements TaskServiceInterface {

    @Autowired
    TaskRepository taskRepository;

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> findById(int id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

    public List<Task> findUpcomingTasks() {
        Instant now = Instant.now();
        Instant before = now.minus(Duration.ofDays(7));
        Date dateBefore = Date.from(before);



        return taskRepository.findUpcomingTasks(dateBefore, new Date());
    }

    public List<Task> findTodaysTasks() {
        Instant now = Instant.now();
        Instant before = now.minus(Duration.ofDays(1));
        Date dateBefore = Date.from(before);

        return taskRepository.findUpcomingTasks(dateBefore, new Date());
    }

    public List<Task> findUpcomingTasks(Date cutoff) {
        return taskRepository.findUpcomingTasks(cutoff, new Date());
    }

}
