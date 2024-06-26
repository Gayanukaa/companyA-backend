package com.companyA.backend.HumanResourceSystem.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Employee")
public class EmployeeDetailModel {

    @Id
    private String employeeId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private int age;
    private String nic;
    private String address;
    private String emailAddress;
    private String phoneNumber; // decide 077 1234567
    private String emergencyContactNumber;
    private String gender; // decide Enum
    private int bankAccountNumber; //
    private String department; // dropdown
    private String jobRole; // dropdown
    private Date recruitmentDate;
    private double salary;
    private boolean permanentStaff;
    private String insuranceCategory;
    private String skillLevel;
    private boolean isActive = true;

    @DBRef
    private TrainingManagementModel trainingManagement;

}
