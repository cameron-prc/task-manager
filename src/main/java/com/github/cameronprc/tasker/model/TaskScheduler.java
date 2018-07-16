package com.github.cameronprc.tasker.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class TaskScheduler {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(targetEntity = TaskTemplate.class)
    @JoinColumn(name="template_id")
    private TaskTemplate taskTemplate;

    private String frequencyCron;

    private Integer daysWarning;

    private Timestamp lastRun;

    private Boolean allowDuplicates;


    public TaskScheduler() {}

    public TaskScheduler(TaskTemplate taskTemplate, String frequencyCron, Integer daysWarning, Timestamp lastRun, Boolean allowDuplicates) {
        this.taskTemplate = taskTemplate;
        this.frequencyCron = frequencyCron;
        this.daysWarning = daysWarning;
        this.lastRun = lastRun;
        this.allowDuplicates = allowDuplicates;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTaskTemplate(TaskTemplate taskTemplate) {
        this.taskTemplate = taskTemplate;
    }

    public void setFrequencyCron(String cron) {
        this.frequencyCron = cron;
    }

    public void setLastRun(Timestamp lastRun) {
        this.lastRun = lastRun;
    }

    public int getId() { return id; }

    public TaskTemplate getTaskTemplate() {
        return taskTemplate;
    }

    public String getFrequencyCron() {
        return this.frequencyCron;
    }

    public Timestamp getLastRun() {
        return lastRun;
    }

    public Boolean getAllowDuplicates() { return allowDuplicates; }

    public Integer getDaysWarning() { return daysWarning; }
}
