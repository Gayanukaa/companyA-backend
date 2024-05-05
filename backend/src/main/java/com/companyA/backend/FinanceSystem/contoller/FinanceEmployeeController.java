package com.companyA.backend.FinanceSystem.contoller;

import com.companyA.backend.FinanceSystem.model.EmployeeSalary;
import com.companyA.backend.FinanceSystem.repository.EmployeeSalaryRepo;
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
@CrossOrigin
public class FinanceEmployeeController {
    @Autowired
    private FinanceEmployeeService financeEmployeeService;

    @Autowired
    private EmployeeSalaryRepo employeeSalaryRepo;

    @GetMapping("/getEmployeeSalary")
    public List<EmployeeSalary> getAllEmployees() {
        return financeEmployeeService.getAllEmployeeSalary();
    }
    @GetMapping("/getEmployeeSalary/{employeeId}")
    public EmployeeSalary getEmployeeSalary(@PathVariable String employeeId) {
        return financeEmployeeService.getEmployeeSalary(employeeId);
    }

    @PostMapping("/SaveEmployeeSalary")
    public ResponseEntity<Map<String, String>> setEmployeeSalary(@RequestBody EmployeeSalary employee) {
        financeEmployeeService.addEmployeeSalary(employee);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Employee Salary is Successfully Added");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/updateSalary")
    public ResponseEntity<Map<String, String>> updateEmployeeSalary(@RequestParam String employeeId,@RequestParam double basicSalary,@RequestParam double otHours, @RequestParam double payForOtHour,@RequestParam int numberOfAbsentDays){
        EmployeeSalary employeeSalary = employeeSalaryRepo.findById(employeeId).orElseThrow(null);
        employeeSalary.setBasicSalary(basicSalary);
        employeeSalary.setOtHours(otHours);
        employeeSalary.setPayForOtHour(payForOtHour);
        employeeSalary.setNumberOfAbsentDays(numberOfAbsentDays);
        financeEmployeeService.updateEmployeeSalary(employeeSalary);

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
