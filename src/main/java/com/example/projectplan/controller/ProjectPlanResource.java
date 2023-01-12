package com.example.projectplan.controller;


import com.example.projectplan.model.dto.ProjectPlanGenDto;
import com.example.projectplan.model.dto.ProjectPlanResponseDto;
import com.example.projectplan.model.dto.ResponseDto;
import com.example.projectplan.service.ProjectPlanService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/project_plan")
@Slf4j
public class ProjectPlanResource {
    @Autowired
    private ProjectPlanService service;

    @GetMapping("/all")
    public ResponseEntity<ResponseDto<List<ProjectPlanGenDto>>> getProjectPlan() {
        log.debug("Getting all project plan");
        //return new ResponseEntity<>(service.searchProjectPlan(), HttpStatus.OK);
        return ResponseEntity.ok(new ResponseDto<>(HttpStatus.OK, service.searchProjectPlan()));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectPlanResponseDto> getProjectPlan(@PathVariable("id") long id) {
        log.debug("Get proj plan by id");

        ProjectPlanResponseDto projectPlanResponseDto = new ProjectPlanResponseDto();
        Set<String> errorMessage = new HashSet<>();
        try {
            projectPlanResponseDto.setProjectPlan(service.searchProjectPlanById(id));
            projectPlanResponseDto.setHttpStatus(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
           errorMessage.add(e.getMessage());
           projectPlanResponseDto.setErroMessages(errorMessage);
            projectPlanResponseDto.setHttpStatus(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(projectPlanResponseDto);
    }

    @PostMapping("/proj/{name}")
    public ResponseEntity<ResponseDto> add(@PathVariable("name") String name) {
        log.debug("Create new project plan");
        try {
            service.addProjectPlan(name);
            return ResponseEntity.ok(new ResponseDto<>(HttpStatus.CREATED, String.format("Project Plan '%s' is was created successfully",name)));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDto<>(HttpStatus.BAD_REQUEST, e.getMessage()));
        }
    }


}
