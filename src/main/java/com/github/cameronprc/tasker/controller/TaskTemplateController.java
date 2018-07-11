package com.github.cameronprc.tasker.controller;

import com.github.cameronprc.tasker.model.TaskTemplate;
import com.github.cameronprc.tasker.service.TaskTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value= "/api/v1/taskTemplates")
public class TaskTemplateController {

    @Autowired
    TaskTemplateService taskTemplateService;

    @GetMapping
    public List<TaskTemplate> getTaskTemplates() {
        return taskTemplateService.findAll();
    }

    @GetMapping(value="/{id}")
    public TaskTemplate getTaskTemplate(@PathVariable Integer id) throws Exception {
        Optional<TaskTemplate> task = taskTemplateService.findById(id);

        if (!task.isPresent()) {
            throw new Exception();
        }

        return task.get();
    }

    @PostMapping
    public ResponseEntity<Object> saveTaskTemplate(@RequestBody TaskTemplate taskTemplate){
        TaskTemplate savedTask = taskTemplateService.saveTaskTemplate(taskTemplate);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedTask.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateTaskTemplate(@PathVariable Integer id, @RequestBody TaskTemplate task){
        Optional<TaskTemplate> taskOptional = taskTemplateService.findById(id);

        if (!taskOptional.isPresent())
            return ResponseEntity.notFound().build();

        task.setId(id);

        taskTemplateService.saveTaskTemplate(task);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Object> deleteTaskTemplate(@PathVariable Integer id) {
        Optional<TaskTemplate> taskOptional = taskTemplateService.findById(id);

        if (!taskOptional.isPresent())
            return ResponseEntity.notFound().build();

        taskTemplateService.deleteTaskTemplate(taskOptional.get());

        return ResponseEntity.noContent().build();
    }
}
