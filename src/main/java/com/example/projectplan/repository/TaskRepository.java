package com.example.projectplan.repository;

import com.example.projectplan.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProjectPlanId(long id);
    Task findByName(String name);


}
