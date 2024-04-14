package com.companyA.backend.FinanceSystem.contoller;

import com.companyA.backend.FinanceSystem.model.EmployeeSalary;
import com.companyA.backend.FinanceSystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/getEmployees")
    public ResponseEntity<List<EmployeeSalary>> getAllEmployees() {

        return new ResponseEntity<List<EmployeeSalary>>(employeeService.getAllEmployee(), HttpStatus.OK);
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<Map<String, String>> setUser(@RequestBody EmployeeSalary employee) {
        employeeService.addEmployee(employee);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Employee is Successfully Added");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
