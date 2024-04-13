package com.companyA.backend.HumanResourceSystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor


public class TrainingManagementModel {

    @Id
    private String id;
    private String department;
    private String skillLevel;
    private String trainingStatus;

}
