package com.example.projectplan.model.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectPlanDto {

    private Long id;

    private String name;

    private String schedule;
}
