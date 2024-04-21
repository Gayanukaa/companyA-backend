package com.companyA.backend.ManufacturingSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private String id;              // Unique identifier for the employee
    private String firstName;       // First name of the employee
    private String lastName;        // Last name of the employee
    private String department;      // Department to which the employee belongs
    // No additional fields needed for current functionalities
}
