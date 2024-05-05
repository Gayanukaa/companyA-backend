package com.companyA.backend.ManufacturingSystem.service;

import com.companyA.backend.ManufacturingSystem.model.Employee;
import com.companyA.backend.ManufacturingSystem.repository.ManufacturingEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Service class to handle employee-related operations
@Service
public class ManufacturingEmployeeService {

    @Autowired
    private final ManufacturingEmployeeRepository manufacturingEmployeeRepository;

    public ManufacturingEmployeeService(ManufacturingEmployeeRepository manufacturingEmployeeRepository) {
        this.manufacturingEmployeeRepository = manufacturingEmployeeRepository;
    }

    // Retrieve employees by department
    public List<Employee> getEmployeesByDepartment(String department) {
        // Delegate the query to the repository
        return manufacturingEmployeeRepository.findBydepartment(department);
    }
}