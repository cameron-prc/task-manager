package com.github.cameronprc.tasker.service;

import com.github.cameronprc.tasker.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskServiceInterface {

    Task saveTask(Task task);

    void deleteTask(Task task);

    Optional<Task> findById(int id);

    List<Task> findAll();

}
