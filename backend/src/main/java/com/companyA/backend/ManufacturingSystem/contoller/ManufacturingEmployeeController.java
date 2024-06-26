package com.companyA.backend.ManufacturingSystem.contoller;

import com.companyA.backend.ManufacturingSystem.model.Employee;
import com.companyA.backend.ManufacturingSystem.service.ManufacturingEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/managers")
@CrossOrigin
public class ManufacturingEmployeeController {

    @Autowired
    private ManufacturingEmployeeService manufacturingEmployeeService;
    // Endpoint to retrieve employees by department for manufacturing
    @GetMapping("/manufacturing")
    public ResponseEntity<List<Employee>> getEmployeesByDepartment(@RequestParam("department") String department) {
        // Retrieve employees by department from the service layer
        List<Employee> manufacturingWorkers = manufacturingEmployeeService.getEmployeesByDepartment(department);
        // Return a successful response with the list of employees
        return ResponseEntity.ok(manufacturingWorkers);
    }
}

