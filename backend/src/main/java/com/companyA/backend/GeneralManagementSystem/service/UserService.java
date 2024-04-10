package com.companyA.backend.GeneralManagementSystem.service;

import com.companyA.backend.GeneralManagementSystem.model.Login;
import com.companyA.backend.GeneralManagementSystem.model.User;
import com.companyA.backend.GeneralManagementSystem.repository.CustomerRepository;
import com.companyA.backend.GeneralManagementSystem.repository.ManagerRepository;
import com.companyA.backend.GeneralManagementSystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
@AllArgsConstructor
public class UserService {

    private CustomerRepository customerRepository;
    private ManagerRepository managerRepository;
    private PasswordEncoder passwordEncoder;


    // Logic for Login for any users in the system
    public ResponseEntity <Map<String, String>> userLogin(Login login) {

        String userRole = login.getRole();
        String userEmail = login.getEmail();
        String userPassword = login.getPassword();

        if (userRole.equals("customer")) {
            return authenticateUser(customerRepository, userEmail, userPassword, "Customer");
        } else if (userRole.equals("manager")) {
            return authenticateUser(managerRepository, userEmail, userPassword, "Manager");
        }

        return sendResponseMessage("Invalid User", HttpStatus.BAD_REQUEST);
    }


    // User Authentication using Generic Type
    public <T extends User> ResponseEntity <Map<String, String>> authenticateUser(UserRepository<T> userRepository,
                                                                                  String userEmail,
                                                                                  String userPassword,
                                                                                  String userRole) {
        T relatedUser = userRepository.findByEmail(userEmail);

        if (relatedUser != null) {
            String encodedPassword = relatedUser.getPassword();
            boolean isPasswordCorrect = passwordEncoder.matches(userPassword, encodedPassword);
            if (isPasswordCorrect) {
                String successMessage =  userRole + " Successfully Logged in";
                return sendResponseMessage(successMessage, HttpStatus.OK);
            }
        }

        String errorMessage = "Authentication failed for " + userRole;
        return sendResponseMessage(errorMessage, HttpStatus.BAD_REQUEST);
    }


    // Separate Method for create the Response
    public ResponseEntity <Map<String, String>> sendResponseMessage (String message, HttpStatus requestStatus) {
        Map <String, String> response = new HashMap<>();
        response.put("message", message);
        response.put("status", String.valueOf(!requestStatus.isError()));
        return new ResponseEntity<>(response, requestStatus);
    }
}
