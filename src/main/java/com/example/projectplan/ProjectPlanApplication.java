package com.example.projectplan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"com.example.projectplan"
})
public class ProjectPlanApplication {

	public static void main(String[] args) {

		SpringApplication.run(ProjectPlanApplication.class, args);

		System.out.println("RUN THE SPRING BOOT APPLICATION");
	}

}
