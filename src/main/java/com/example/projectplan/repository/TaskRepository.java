package com.example.projectplan.repository;

import com.example.projectplan.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;


public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProjectPlanId(long id);
    Task findByName(String name);


}
