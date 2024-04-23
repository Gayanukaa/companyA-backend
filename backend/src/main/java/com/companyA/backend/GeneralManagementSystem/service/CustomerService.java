package com.companyA.backend.GeneralManagementSystem.service;

import com.companyA.backend.GeneralManagementSystem.DTO.CustomerDTO;
import com.companyA.backend.GeneralManagementSystem.DTO.ManagerDTO;
import com.companyA.backend.GeneralManagementSystem.model.Customer;
import com.companyA.backend.GeneralManagementSystem.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<CustomerDTO>getAllCustomers(){
        List<Customer>customers = customerRepository.findAll();
        List<CustomerDTO>customerDTOS=new ArrayList<>();
        for(Customer customer:customers){
            CustomerDTO dto = new CustomerDTO();
            dto.setId(customer.getId());
            dto.setFirstName(customer.getFirstName());
            dto.setLastName(customer.getLastName());
            dto.setEmail(customer.getEmail());
            customerDTOS.add(dto);
        }
        return customerDTOS;
    }


}
