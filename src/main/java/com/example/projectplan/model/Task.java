package com.example.projectplan.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task")
public class Task {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @SequenceGenerator(name="TASK_SEQ", initialValue = 9)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private String status;

    private LocalDate start_date;

    private LocalDate end_date;
    @Transient
    private String project_plan_id;
    @ManyToOne
    @JoinColumn(name = "project_plan_id", nullable = false, referencedColumnName = "id")
    //@JsonBackReference
    private ProjectPlan projectPlan;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="task_id", referencedColumnName = "id")
    @JsonBackReference
    @ToString.Exclude
    private List<Task> dependency; // relate to Task itself


}
