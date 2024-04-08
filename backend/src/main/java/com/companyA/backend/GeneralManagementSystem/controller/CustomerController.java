package com.companyA.backend.GeneralManagementSystem.controller;

import com.companyA.backend.GeneralManagementSystem.model.Customer;
import com.companyA.backend.GeneralManagementSystem.model.User;
import com.companyA.backend.GeneralManagementSystem.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {

    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<Map <String, String>> customerRegisterControl(@RequestBody Customer customer) {
        String registrationResult = customerService.customerRegister(customer);

        Map<String, String> response = new HashMap<>();
        response.put("message", registrationResult);
        return ResponseEntity.ok(response);
    }
}
