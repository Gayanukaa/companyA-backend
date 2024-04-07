package com.companyA.backend.GeneralManagementSystem.controller;

import com.companyA.backend.GeneralManagementSystem.model.User;
import com.companyA.backend.GeneralManagementSystem.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {

    private CustomerService customerService;

    @PostMapping("/register")
    public String customerRegisterControl(@RequestBody User user) {
        customerService.customerRegister(user);
        return "Registered Successfully";
    }
}
