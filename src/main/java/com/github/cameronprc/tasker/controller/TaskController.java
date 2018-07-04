package com.github.cameronprc.tasker.controller;

import com.github.cameronprc.tasker.model.Task;
import com.github.cameronprc.tasker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping
    public List<Task> getTasks(){
        return taskService.findAll();
    }

    @GetMapping(value = "/upcoming")
    public List<Task> getUpcomingTasks(){
        return taskService.findUpcomingTasks();
    }

    @GetMapping(value = "/todays")
    public List<Task> getTodaysTasks(){
        return taskService.findTodaysTasks();
    }

    @GetMapping(value="/{id}")
    public Task getTask(@PathVariable Integer id) throws Exception {
        Optional<Task> task = taskService.findById(id);

        if (!task.isPresent()) {
            throw new Exception();
        }

        return task.get();
    }

    @PostMapping
    public ResponseEntity<Object> saveTask(@RequestBody Task task){
        Task savedTask = taskService.saveTask(task);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedTask.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateTask(@PathVariable Integer id, @RequestBody Task task){
        Optional<Task> taskOptional = taskService.findById(id);

        if (!taskOptional.isPresent())
            return ResponseEntity.notFound().build();

        task.setId(id);

        taskService.saveTask(task);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable Integer id) {
        Optional<Task> taskOptional = taskService.findById(id);

        if (!taskOptional.isPresent())
            return ResponseEntity.notFound().build();

        taskService.deleteTask(taskOptional.get());

        return ResponseEntity.noContent().build();
    }
}
