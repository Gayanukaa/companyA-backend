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

    @GetMapping("/getEmployeeSalary")
    public List<EmployeeSalary> getAllEmployees() {
        return employeeService.getAllEmployee();
    }
    @GetMapping("/getEmployeeSalary/{employeeId}")
    public EmployeeSalary getEmployeeSalary(@PathVariable int employeeId) {
        return employeeService.getEmployee(employeeId);
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<Map<String, String>> setEmployeeSalary(@RequestBody EmployeeSalary employee) {
        employeeService.addEmployee(employee);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Employee Salary is Successfully Added");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/updateSalary")
    public ResponseEntity<Map<String, String>> updateEmployeeSalary(@RequestBody EmployeeSalary employeeSalary){
        employeeService.updateEmployee(employeeSalary);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Employee Salary is Successfully Updated");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
