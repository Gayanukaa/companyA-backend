package com.companyA.backend.ManufacturingSystem.contoller;

import com.companyA.backend.ManufacturingSystem.model.Employee;
import com.companyA.backend.ManufacturingSystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/managers")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    // Endpoint to retrieve employees by department for manufacturing
    @GetMapping("/manufacturing")
    public ResponseEntity<List<Employee>> getEmployeesByDepartment(@RequestParam("department") String department) {
        // Retrieve employees by department from the service layer
        List<Employee> manufacturingWorkers = employeeService.getEmployeesByDepartment(department);
        // Return a successful response with the list of employees
        return ResponseEntity.ok(manufacturingWorkers);
    }
}

