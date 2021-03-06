package com.github.cameronprc.tasker.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class TaskTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String description;

    private Integer reminderDays;


    protected TaskTemplate() {}

    public TaskTemplate(String name, String description, Integer reminderDays) {
        this.name = name;
        this.description = description;
        this.reminderDays = reminderDays;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Integer getReminderDays() { return reminderDays; }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
