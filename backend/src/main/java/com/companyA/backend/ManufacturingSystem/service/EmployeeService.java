package com.companyA.backend.ManufacturingSystem.service;

import com.companyA.backend.ManufacturingSystem.model.Employee;
import com.companyA.backend.ManufacturingSystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Service class to handle employee-related operations
@Service
public class EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Retrieve employees by department
    public List<Employee> getEmployeesByDepartment(String department) {
        // Delegate the query to the repository
        return employeeRepository.findBydepartment(department);
    }
}
