package com.companyA.backend.FinanceSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    private int _id;
    private String employeeName;
    private String employeeEmail;
    private String employeeDesignation;
    private double normalHours;
    private double otHours;
    private EmployeeSalary salary;

}
