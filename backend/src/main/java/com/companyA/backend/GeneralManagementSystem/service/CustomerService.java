package com.companyA.backend.GeneralManagementSystem.service;

import com.companyA.backend.GeneralManagementSystem.model.User;
import com.companyA.backend.GeneralManagementSystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private UserRepository userRepository;

    public void customerRegister(User user) {
        userRepository.save(user);
    }

}
