package com.companyA.backend.GeneralManagementSystem.service;

import com.companyA.backend.GeneralManagementSystem.model.Customer;
import com.companyA.backend.GeneralManagementSystem.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;
    private PasswordEncoder passwordEncoder;

    public String customerRegister(Customer customer) {

        if (customerRepository.existsByEmail((String) customer.getEmail())) {
           return "Email already exists";
        } else {
            String hashedPassword = passwordEncoder.encode(customer.getPassword());
            customer.setPassword(hashedPassword);
            customerRepository.save(customer);
            return "Customer Successfully Registered";
        }
    }


    public long countCustomers(){
        return customerRepository.count();
    }


}
