package com.example.projectplan.repository;

import com.example.projectplan.model.ProjectPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectPlanRepository extends JpaRepository<ProjectPlan, Long> {

    ProjectPlan findByName(String name);
}
