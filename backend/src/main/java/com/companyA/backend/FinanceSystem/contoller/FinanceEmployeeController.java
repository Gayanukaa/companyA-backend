package com.companyA.backend.FinanceSystem.contoller;

import com.companyA.backend.FinanceSystem.model.EmployeeSalary;
import com.companyA.backend.FinanceSystem.service.FinanceEmployeeService;
import com.companyA.backend.FinanceSystem.service.IDNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class FinanceEmployeeController {
    @Autowired
    private FinanceEmployeeService financeEmployeeService;

    @GetMapping("/getEmployeeSalary")
    public List<EmployeeSalary> getAllEmployees() {
        return financeEmployeeService.getAllEmployee();
    }
    @GetMapping("/getEmployeeSalary/{employeeId}")
    public EmployeeSalary getEmployeeSalary(@PathVariable String employeeId) {
        return financeEmployeeService.getEmployee(employeeId);
    }

    @PostMapping("/SaveEmployeeSalary")
    public ResponseEntity<Map<String, String>> setEmployeeSalary(@RequestBody EmployeeSalary employee) {
        financeEmployeeService.addEmployee(employee);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Employee Salary is Successfully Added");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/updateSalary")
    public ResponseEntity<Map<String, String>> updateEmployeeSalary(@RequestBody EmployeeSalary employeeSalary){
        financeEmployeeService.updateEmployee(employeeSalary);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Employee Salary is Successfully Updated");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleEmployeeNotFoundException(IDNotFoundException ex) {
        // Create a custom response for EmployeeNotFoundException
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
