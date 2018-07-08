package com.github.cameronprc.tasker.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class TaskScheduler {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="template_id")
    private TaskTemplate taskTemplate;

    private String cron;

    private Timestamp lastRun;

    public TaskScheduler() {}

    public TaskScheduler(TaskTemplate taskTemplate, String cron, Timestamp lastRun) {
        this.taskTemplate = taskTemplate;
        this.cron = cron;
        this.lastRun = lastRun;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTaskTemplate(TaskTemplate taskTemplate) {
        this.taskTemplate = taskTemplate;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public void setLastRun(Timestamp lastRun) {
        this.lastRun = lastRun;
    }

    public int getId() {

        return id;
    }

    public TaskTemplate getTaskTemplate() {
        return taskTemplate;
    }

    public String getCron() {
        return cron;
    }

    public Timestamp getLastRun() {
        return lastRun;
    }
}
