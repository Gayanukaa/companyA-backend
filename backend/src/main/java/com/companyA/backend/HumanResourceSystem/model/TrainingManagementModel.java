package com.companyA.backend.HumanResourceSystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "TrainingManagement")
public class TrainingManagementModel {


    private String courseId;
    private String courseName;
    private String department;
    private String skillLevel;


    @DBRef
    private List<EmployeeDetailModel> employees;
}
