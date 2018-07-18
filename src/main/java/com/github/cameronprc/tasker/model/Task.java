package com.github.cameronprc.tasker.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String description;

    private Date dueDate;

    private Boolean completed;

    private Integer reminderDays;


    protected Task() {}

    public Task(String name, String description, Date dueDate, Boolean completed) {
        this(name, description, dueDate, null, completed);
    }

    public Task(String name, String description, Date dueDate, Integer reminderDays, Boolean completed) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.reminderDays = reminderDays != null ? reminderDays : 0;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(Date date) {
        this.dueDate = date;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dueDate=" + dueDate +
                ", completed=" + completed +
                '}';
    }
}
