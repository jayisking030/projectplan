package com.example.projectplan.service.impl;

import com.example.projectplan.exception.ProjectPlanException;
import com.example.projectplan.mapper.ProjectPlanMapper;
import com.example.projectplan.model.ProjectPlan;
import com.example.projectplan.model.Task;
import com.example.projectplan.model.dto.ProjectPlanDto;
import com.example.projectplan.model.dto.ProjectPlanGenDto;
import com.example.projectplan.repository.ProjectPlanRepository;
import com.example.projectplan.service.ProjectPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectPlanServiceImpl implements ProjectPlanService {
    @Autowired
    private ProjectPlanRepository repository;
    @Autowired
    private ProjectPlanMapper mapper;

    @Override
    public List<ProjectPlanGenDto> searchProjectPlan() {
        return repository.findAll()
                .stream()
                .map(mapper::toProjectPlanGenDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectPlanDto searchProjectPlanById(long id) {

        Optional<ProjectPlan> projectPlan = repository.findById(id);

        ProjectPlanDto projectPlanDto = new ProjectPlanDto();
        ProjectPlan projectPlan1 = null;
        String schedule = "0 Days";

        if(!projectPlan.isEmpty()){
            projectPlan1 = projectPlan.get();

            List<Task> taskList = projectPlan1.getTaskList();
            if(!taskList.isEmpty()){
                LocalDate minStartDate = taskList.stream().map(Task::getStart_date).min(Comparator.naturalOrder()).get();
                LocalDate maxEndDate = taskList.stream().map(Task::getEnd_date).max(Comparator.naturalOrder()).get();

                 Long daysInBetween = ChronoUnit.DAYS.between(minStartDate,maxEndDate);
                 schedule = daysInBetween.toString().concat(" Days");

            }
        }else{
            throw new ProjectPlanException(String.format("Unable to find Project Plan with id [%s]", id));
        }
        return ProjectPlanDto.builder().id(projectPlan1.getId()).name(projectPlan1.getName()).schedule(schedule).build();
    }

    @Override
    public void addProjectPlan(String name) {
        ProjectPlan projectPlan = Optional.ofNullable(repository.findByName(name)).orElse(null);
        if(projectPlan == null){
             repository.save(ProjectPlan.builder().name(name).build());
        }else{
            throw new ProjectPlanException(String.format("Project plan with name [%s] already exist.",name));
        }

    }
}
