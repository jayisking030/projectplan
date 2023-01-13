package com.example.projectplan.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project_plan")
public class ProjectPlan {

    public ProjectPlan(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProjectPlan( String name) {
        this.name = name;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@SequenceGenerator(name="PROJECT_PLAN_SEQ", initialValue = 6)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    @OneToMany(mappedBy = "projectPlan")
    //@JsonManagedReference
    private List<Task> taskList;
}
