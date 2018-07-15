package com.github.cameronprc.tasker.repository;

import com.github.cameronprc.tasker.model.TaskScheduler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskSchedulerRepository extends JpaRepository<TaskScheduler, Integer> {
}
