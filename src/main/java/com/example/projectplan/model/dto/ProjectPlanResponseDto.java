package com.example.projectplan.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectPlanResponseDto<T> {

    HttpStatus httpStatus;
    T  projectPlan;
    Set<String> erroMessages;

}
