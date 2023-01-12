package com.example.projectplan.service;

import com.example.projectplan.model.ProjectPlan;
import com.example.projectplan.model.dto.ProjectPlanDto;
import com.example.projectplan.model.dto.ProjectPlanGenDto;

import java.util.List;
import java.util.Optional;

public interface ProjectPlanService {

    List<ProjectPlanGenDto> searchProjectPlan();

    ProjectPlanDto searchProjectPlanById(long id);

    void addProjectPlan(String name);
}
