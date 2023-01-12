package com.example.projectplan.service;
import com.example.projectplan.model.dto.TaskDto;
import com.example.projectplan.model.dto.TaskRequestDto;
import com.example.projectplan.model.dto.TaskResponseDto;

import java.util.List;

public interface TaskService {
    List<TaskDto> searchAllTask();

    List<TaskDto> findallTaskByProjId(long id);

    TaskResponseDto addTaskByProjId(long id, TaskRequestDto taskRequestDto);

    TaskResponseDto updateTask(long id, TaskRequestDto taskRequestDto);

    TaskDto getTaskById(long id);
}
