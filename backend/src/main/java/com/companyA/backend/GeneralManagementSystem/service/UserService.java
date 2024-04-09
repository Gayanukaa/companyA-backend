package com.companyA.backend.GeneralManagementSystem.service;

import com.companyA.backend.GeneralManagementSystem.model.Customer;
import com.companyA.backend.GeneralManagementSystem.model.Login;
import com.companyA.backend.GeneralManagementSystem.model.Manager;
import com.companyA.backend.GeneralManagementSystem.repository.CustomerRepository;
import com.companyA.backend.GeneralManagementSystem.repository.ManagerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserService {

    private CustomerRepository customerRepository;

    public String userLogin(Login login) {

        String userRole = login.getRole();
        String userEmail = login.getEmail();
        String userPassword = login.getPassword();

        if (userRole.equals("customer")) {
            Customer relatedCustomer = customerRepository.findByEmail(userEmail);

            if (relatedCustomer != null) {
                if (userPassword.equals(relatedCustomer.getPassword())) {
                    return "Customer Successfully Logged in";
                }
            }

        } else if (userRole.equals("manager")) {
            return "Hi manager";
        }

        return "done";
    }
}
