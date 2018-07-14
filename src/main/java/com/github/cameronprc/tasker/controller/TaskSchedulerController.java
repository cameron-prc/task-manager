package com.github.cameronprc.tasker.controller;

import com.github.cameronprc.tasker.model.TaskScheduler;
import com.github.cameronprc.tasker.model.TaskTemplate;
import com.github.cameronprc.tasker.service.TaskSchedulerService;
import com.github.cameronprc.tasker.service.TaskTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value= "/api/v1/taskSchedulers")
public class TaskSchedulerController {

    @Autowired
    TaskSchedulerService taskSchedulerService;

    @GetMapping
    public List<TaskScheduler> getTaskSchedules() {
        return taskSchedulerService.findAll();
    }

    @GetMapping(value="/{id}")
    public TaskScheduler getTaskScheduler(@PathVariable Integer id) throws Exception {
        Optional<TaskScheduler> task = taskSchedulerService.findById(id);

        if (!task.isPresent()) {
            throw new Exception();
        }

        return task.get();
    }

    @PostMapping
    public ResponseEntity<Object> saveTaskScheduler(@RequestBody TaskScheduler taskScheduler){
        TaskScheduler savedTaskScheduler = taskSchedulerService.saveTaskScheduler(taskScheduler);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedTaskScheduler.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateTaskTemplate(@PathVariable Integer id, @RequestBody TaskScheduler taskScheduler){
        Optional<TaskScheduler> taskOptional = taskSchedulerService.findById(id);

        if (!taskOptional.isPresent())
            return ResponseEntity.notFound().build();

        taskScheduler.setId(id);

        taskSchedulerService.saveTaskScheduler(taskScheduler);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Object> deleteTaskScheduler(@PathVariable Integer id) {
        Optional<TaskScheduler> taskOptional = taskSchedulerService.findById(id);

        if (!taskOptional.isPresent())
            return ResponseEntity.notFound().build();

        taskSchedulerService.deleteTaskScheduler(taskOptional.get());

        return ResponseEntity.noContent().build();
    }
}
