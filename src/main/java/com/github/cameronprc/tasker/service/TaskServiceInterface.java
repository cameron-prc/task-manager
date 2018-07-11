package com.github.cameronprc.tasker.service;

import com.github.cameronprc.tasker.model.Task;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TaskServiceInterface {
    List<Task> findAll();

    Optional<Task> findById(int id);

    Task saveTask(Task task);

    void deleteTask(Task task);

    List<Task> findUpcomingTasks();

    List<Task> findTodaysTasks();

    List<Task> findUpcomingTasks(Date cutoff);
}
