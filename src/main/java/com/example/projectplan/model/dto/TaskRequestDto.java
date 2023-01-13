package com.example.projectplan.model.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;


import java.util.Date;

@Data
public class TaskRequestDto {

    private Long id;
    private String name;
    private String task_dependency;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date start_date;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date end_date;
}

