package com.companyA.backend.FinanceSystem.contoller;

import com.companyA.backend.FinanceSystem.model.EmployeeSalary;
import com.companyA.backend.FinanceSystem.repository.PaymentRepo;
import com.companyA.backend.FinanceSystem.repository.StockAlertRepo;
import com.companyA.backend.FinanceSystem.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;



    @PostMapping("/salary/{employeeId}")
    public ResponseEntity<Map<String, String>> EmployeeSalaryConfirmation(@PathVariable int employeeId) {
        paymentService.SalaryPaymentConfirmation(employeeId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Employee Salary is Successfully Deposited");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
