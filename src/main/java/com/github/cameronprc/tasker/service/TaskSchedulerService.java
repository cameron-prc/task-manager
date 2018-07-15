package com.github.cameronprc.tasker.service;

import com.cronutils.model.definition.CronDefinition;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.model.time.ExecutionTime;
import com.cronutils.parser.CronParser;
import com.github.cameronprc.tasker.model.Task;
import com.github.cameronprc.tasker.model.TaskScheduler;
import com.github.cameronprc.tasker.model.TaskTemplate;
import com.github.cameronprc.tasker.repository.TaskSchedulerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.cronutils.model.CronType.QUARTZ;

@Service
public class TaskSchedulerService {

    @Autowired
    private TaskSchedulerRepository taskSchedulerRepository;

    @Autowired
    private TaskService taskService;

    public List<TaskScheduler> findAll() {
        return taskSchedulerRepository.findAll();
    }

    public Optional<TaskScheduler> findById(int id) {
        return taskSchedulerRepository.findById(id);
    }

    public TaskScheduler saveTaskScheduler(TaskScheduler taskScheduler) {
        return taskSchedulerRepository.save(taskScheduler);
    }

    public void deleteTaskScheduler(TaskScheduler taskScheduler) {
        taskSchedulerRepository.delete(taskScheduler);
    }

    @Scheduled(cron = "0 0 * * * ?")
    private void createScheduledTasks() {
        System.out.println("In scheduled task");
        List<TaskScheduler> schedulers = findAll();

        for (TaskScheduler taskScheduler: schedulers) {
            // Use the task schedulers last run time and cron to calculate the next execution time from its last run date

            // Convert the last run time java.sql.timestamp to a ZonedDateTime as required by the
            // com.cronutils.model.time.ExecutionTime library
            LocalDateTime withoutTimezone = taskScheduler.getLastRun().toLocalDateTime();
            ZonedDateTime withTimezone = withoutTimezone.atZone(ZoneId.systemDefault());

            // Create parser for taskSchedulers cron value
            CronDefinition cronDefinition = CronDefinitionBuilder.instanceDefinitionFor(QUARTZ);
            CronParser parser = new CronParser(cronDefinition);

            // Calculate the next execution time for the task scheduler from the last run time
            ExecutionTime executionTime = ExecutionTime.forCron(parser.parse(taskScheduler.getCron()));
            Optional<ZonedDateTime> nextExecution = executionTime.nextExecution(withTimezone);

            // If the the current time is greater the the next scheduled execution time, initiate the creation
            if(nextExecution.isPresent() && nextExecution.get().isBefore(ZonedDateTime.now())) {
                TaskTemplate template = taskScheduler.getTaskTemplate();

                // Determine if an instance of the task already exists
                // If it exists, check to see if duplicates are allowed. If not, abort creation
                if(!taskService.findByName(template.getName()).isEmpty()) {
                    if(!taskScheduler.getAllowDuplicates()) {
                        return;
                    }
                }

                // Create the due date for the new task
                Instant now = Instant.now();
                Instant before = now.plus(Duration.ofDays(7)); // TODO: Add ability to specify this through the scheduler/template
                Date date = Date.from(before);

                Task newTask = new Task(template.getName(), template.getDescription(), date, false);

                taskService.saveTask(newTask);
            }
        }
    }
}
