package com.companyA.backend.HumanResourceSystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "TrainingManagement")
public class TrainingManagementModel {

    @Id
    private String id;
    private String employeeId;
    private String skillLevel;

    private EmployeeDetailModel employeeDetailModel;

}
