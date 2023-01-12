package com.example.projectplan.mapper;


import com.example.projectplan.model.ProjectPlan;
import com.example.projectplan.model.Task;
import com.example.projectplan.model.dto.ProjectPlanDto;
import com.example.projectplan.model.dto.ProjectPlanGenDto;
import com.example.projectplan.model.dto.TaskDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectPlanMapper {

    ProjectPlanDto toDto(ProjectPlan projectPlan);

   ProjectPlanGenDto toProjectPlanGenDto(ProjectPlan projectPlan);

    TaskDto toDto(Task task);

    Task toEntity(TaskDto taskDto);


}
