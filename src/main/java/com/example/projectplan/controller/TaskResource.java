package com.example.projectplan.controller;



import com.example.projectplan.model.dto.TaskDto;
import com.example.projectplan.model.dto.TaskRequestDto;
import com.example.projectplan.model.dto.TaskResponseDto;
import com.example.projectplan.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/task")
@Slf4j
public class TaskResource {

    @Autowired
    TaskService service;

    @GetMapping("/all")
    public ResponseEntity<TaskResponseDto<List<TaskDto>>> getAllTask() {
        log.debug("Getting all task");
        Set<String> errorMessage = new HashSet<>();
        List<TaskDto> taskDtoList;
        TaskResponseDto taskResponseDto = new TaskResponseDto();

        try {
            taskDtoList = service.searchAllTask();
            taskResponseDto.setHttpStatus(HttpStatus.OK);
            taskResponseDto.setTask(taskDtoList);
        } catch (Exception e) {
            errorMessage.add(e.getMessage());
            taskResponseDto.setHttpStatus(HttpStatus.BAD_REQUEST);
            taskResponseDto.setErroMessages(errorMessage);
        }
        return ResponseEntity.ok(taskResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable("id") long id) {
        log.debug("Getting task by id");
        Set<String> errorMessage = new HashSet<>();
        TaskResponseDto taskResponseDto = new TaskResponseDto();
        try {
            taskResponseDto.setTask(service.getTaskById(id));
            taskResponseDto.setHttpStatus(HttpStatus.OK);
        } catch (Exception e) {
            errorMessage.add(e.getMessage());
            taskResponseDto.setHttpStatus(HttpStatus.OK);
            taskResponseDto.setErroMessages(errorMessage);
        }

        return ResponseEntity.ok(taskResponseDto);
    }

    @GetMapping("/proj/{id}")
    public ResponseEntity<TaskResponseDto> getTaskByProjId(@PathVariable("id") long id) {
        log.debug("Getting all task by project_plan id {}", id);

        log.debug("Getting all task");
        Set<String> errorMessage = new HashSet<>();
        List<TaskDto> taskDtoList;
        TaskResponseDto taskResponseDto = new TaskResponseDto();

        try {
            taskDtoList = service.findallTaskByProjId(id);
            taskResponseDto.setHttpStatus(HttpStatus.OK);
            taskResponseDto.setTask(taskDtoList);
        } catch (Exception e) {
            errorMessage.add(e.getMessage());
            taskResponseDto.setHttpStatus(HttpStatus.BAD_REQUEST);
            taskResponseDto.setErroMessages(errorMessage);
        }
        return ResponseEntity.ok(taskResponseDto);
    }

    @PostMapping("/proj/{id}")
    public ResponseEntity<TaskResponseDto> createTask(@PathVariable("id") long id, @RequestBody TaskRequestDto taskRequestDto) {
        log.debug("Save task by project_plan id {}", id);
        TaskResponseDto taskResponseDto = new TaskResponseDto();
        Set<String> errorMessage = new HashSet<>();
        try {
            taskResponseDto = service.addTaskByProjId(id, taskRequestDto);
            errorMessage.addAll(taskResponseDto.getErroMessages());
        } catch (Exception e) {
            errorMessage.add(e.getMessage());
            e.printStackTrace();
        }

        taskResponseDto.setHttpStatus(errorMessage.isEmpty() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
        taskResponseDto.setErroMessages(errorMessage);
        return ResponseEntity.ok(taskResponseDto);
    }

    @PutMapping("/proj/{id}")
    public ResponseEntity<TaskResponseDto> updateTask(@PathVariable("id") long id, @RequestBody TaskRequestDto taskRequestDto) {
        log.debug("Update task by project_plan id {}", id);
        TaskResponseDto taskResponseDto = new TaskResponseDto();
        Set<String> errorMessage = new HashSet<>();
        try {
            taskResponseDto = service.addTaskByProjId(id, taskRequestDto);
            errorMessage.addAll(taskResponseDto.getErroMessages());
        } catch (Exception e) {
            errorMessage.add(e.getMessage());
            e.printStackTrace();
        }

        taskResponseDto.setHttpStatus(errorMessage.isEmpty() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
        taskResponseDto.setErroMessages(errorMessage);
        return ResponseEntity.ok(taskResponseDto);
    }
}
