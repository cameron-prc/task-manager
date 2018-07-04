package com.github.cameronprc.tasker.repository;

import com.github.cameronprc.tasker.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findByName(String name);

    @Query("SELECT t FROM Task t WHERE t.dueDate BETWEEN :fromDate AND :toDate")
    List<Task> findUpcomingTasks(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);
}
