package com.example.projectplan.service.impl;


import com.example.projectplan.mapper.ProjectPlanMapper;
import com.example.projectplan.model.ProjectPlan;
import com.example.projectplan.model.Task;
import com.example.projectplan.model.dto.TaskDto;
import com.example.projectplan.model.dto.TaskRequestDto;
import com.example.projectplan.model.dto.TaskResponseDto;
import com.example.projectplan.repository.ProjectPlanRepository;
import com.example.projectplan.repository.TaskRepository;
import com.example.projectplan.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository repository;

    @Autowired
    private ProjectPlanMapper mapper;

    @Autowired
    private ProjectPlanRepository projectPlanRepository;


    private String DATE_FORMAT = "yyyy-MM-dd";
    String pattern = "yyyy-MM-dd";
    SimpleDateFormat sdf = new SimpleDateFormat(pattern);

    private final String delimeter = ",";


    @Override
    public List<TaskDto> searchAllTask() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> findallTaskByProjId(long id) {
        return repository.findByProjectPlanId(id)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public TaskResponseDto addTaskByProjId(long id, TaskRequestDto taskRequestDto) {
        return this.saveUpdateTask(id,taskRequestDto, false);
    }

    @Override
    public TaskResponseDto updateTask(long id, TaskRequestDto taskRequestDto) {
        return this.saveUpdateTask(id,taskRequestDto, true);
    }


    public TaskResponseDto saveUpdateTask(long id, TaskRequestDto taskRequestDto, boolean isForUpdate) {
        Optional<ProjectPlan> projectPlan = projectPlanRepository.findById(id);
        Set<String> errorMessages = new HashSet<>();
        Task task = new Task();

        if (projectPlan.isPresent()) {
            List<TaskDto> taskDtoList = this.findallTaskByProjId(id);
            Set taskNameSet = getAllOfTaskNameByProj(id, taskDtoList);

            // Check if task name does not exist yet in current proj plan

            Set validateSet = validateFieldsForSaving(taskRequestDto, errorMessages);
            if (validateSet.isEmpty()) {

                Set<String> taskNameUnique =  isTaskNameUnique(taskRequestDto, taskNameSet , errorMessages, isForUpdate);

                if (taskNameUnique.isEmpty()) {
                    //Check if  Dependent task exist
                    Set<String> taskDepValidated = validateTaskDependency(taskRequestDto, taskNameSet, errorMessages);

                    if (taskDepValidated.isEmpty()) {

                        List<Task> dependencyList = new ArrayList<>();
                        if(taskRequestDto.getTask_dependency() != null){
                            getListofTaskByName(taskRequestDto.getTask_dependency(), dependencyList);
                        }
                        LocalDate startDate = LocalDate.now();

                        //calculate schedule for start date and end date
                        if(!dependencyList.isEmpty()){
                            // Task can be started after all dependent tasks are completed
                            LocalDate a = dependencyList.stream().map(Task::getEnd_date).max(Comparator.naturalOrder()).get();
                            startDate =  dependencyList.stream().map(Task::getEnd_date).max(Comparator.naturalOrder()).get().plusDays(1);
                        }

                        LocalDate endDate =  startDate.plusDays(7);

                        // Assign Start and End Dates for every task
                        task = repository.save(Task.builder().name(taskRequestDto.getName())
                                .status("Not Started")
                                .start_date(startDate)
                                .end_date(endDate)
                                .projectPlan(projectPlan.get())
                                .dependency(dependencyList)
                                .build());
                    }
                }
            }
        } else {
            errorMessages.add(String.format("Project Plan with id [%s] does not exist", id));
        }
        TaskDto taskDto = errorMessages.isEmpty() ? mapper.toDto(task) : null;

        return TaskResponseDto.builder()
                .task(taskDto)
                .erroMessages(errorMessages).build();
    }

    Set<String>  isTaskNameUnique(TaskRequestDto taskRequestDto, Set taskNameSet, Set<String> errorMessages , boolean isForUpdate) {

        boolean uniqueTaskName = taskNameSet.add(taskRequestDto.getName());

        if(isForUpdate){
            if(uniqueTaskName){
                errorMessages.add(String.format("Task Name : %s does exist in Project Plan", taskRequestDto.getName()));
            }
        }else{
            if(!uniqueTaskName){
                errorMessages.add(String.format("Task Name : %s already exist in Project Plan", taskRequestDto.getName()));
            }
        }

        return errorMessages;
    }

    Set<String> validateFieldsForSaving(TaskRequestDto taskRequestDto, Set<String> errorMessages) {


        if (taskRequestDto.getName().length() < 1 || taskRequestDto.getName().length() > 50) {
            errorMessages.add("Task Name must not be blank or exceed more than 50 characters");
        }

        return errorMessages;
    }


    @Override
    public TaskDto getTaskById(long id) {
        Optional<Task> task = repository.findById(id);
        Task task2 = task.get();
        List<TaskDto> taskDtoList = task2.getDependency().stream().map(mapper::toDto).collect(Collectors.toList());
        return TaskDto.builder().id(task2.getId())
                .name(task2.getName())
                .status(task2.getStatus())
                .dependency(taskDtoList)
                .start_date(task2.getStart_date())
                .end_date(task2.getEnd_date()).build();
    }

    Set<String> validateTaskDependency(TaskRequestDto taskRequestDto, Set<String> taskNameSet, Set<String> errorMessages) {

        String dependencyString = taskRequestDto.getTask_dependency();
        if (dependencyString != null) {
            Set<String> dependencySet = new HashSet(Arrays.asList(taskRequestDto.getTask_dependency().split(delimeter)));


            dependencySet.forEach(dependency -> {

                if (!dependency.equals("") && taskNameSet.add(dependency) == true) {
                    errorMessages.add(String.format("Dependency with Task Name [%s] does not exist in current Project Plan", dependency));
                }

                if (dependency.equals(taskRequestDto.getName())) {
                    errorMessages.add(String.format("Dependency with Task Name [%s] cannot have itself as its dependency", dependency));
                }
            });

        }

        return errorMessages;
    }


    Set<String> getAllOfTaskNameByProj(long id, List<TaskDto> taskDtoList) {
        return taskDtoList.stream().map(TaskDto::getName)
                .collect(Collectors.toSet());
    }

    List<Task> getListofTaskByName(String taskDependency,List<Task> taskDependencyList) {

        if (taskDependency != null) {
            List<String> dependencyList = new ArrayList(Arrays.asList(taskDependency.split(delimeter)));


            if (!dependencyList.isEmpty()) {
                dependencyList.forEach(dependency -> {
                    Task task = repository.findByName(dependency);
                    taskDependencyList.add(task);

                });

            }
        }
        return taskDependencyList;
    }


}
