package com.companyA.backend.GeneralManagementSystem.service;

import com.companyA.backend.GeneralManagementSystem.DTO.LoginDTO;
import com.companyA.backend.GeneralManagementSystem.model.Manager;
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
    public ResponseEntity <Map<String, String>> userLogin(LoginDTO loginDTO) {

        String userRole = loginDTO.getRole();
        String userEmail = loginDTO.getEmail();
        String userPassword = loginDTO.getPassword();

        if (userRole != null) {
            if (userRole.equals("customer")) {
                return authenticateUser(customerRepository, userEmail, userPassword, userRole);
            } else if (userRole.equals("manager")) {
                return authenticateUser(managerRepository, userEmail, userPassword, userRole);
            }

            return sendResponseMessage("Invalid User", null,null, HttpStatus.BAD_REQUEST);
        }

        return sendResponseMessage("User Role is null", null, null, HttpStatus.BAD_REQUEST);
    }


    // User Authentication using Generic Type
    public <T extends User> ResponseEntity<Map<String, String>> authenticateUser(UserRepository<T> userRepository,
                                                                                 String userEmail,
                                                                                 String userPassword,
                                                                                 String userRole) {
        try {
            T relatedUser = userRepository.findByEmail(userEmail);

            if (relatedUser != null) {

                if (userRole.equals("manager")) {
                    Manager userManager = (Manager) relatedUser;
                    if (userManager.getIsDeleted() == 1) {
                        return sendResponseMessage("Manager is deleted", null, null, HttpStatus.BAD_REQUEST);
                    }
                }

                    String encodedPassword = relatedUser.getPassword();
                    boolean isPasswordCorrect = passwordEncoder.matches(userPassword, encodedPassword);

                    if (isPasswordCorrect) {
                        String returnRole = relatedUser.getRole();
                        String userId = relatedUser.getId();
                        String successMessage = "Successfully Logged in";

                        if (returnRole != null) {
                            return sendResponseMessage(successMessage, userId, returnRole, HttpStatus.OK);
                        }
                    }
                }

            String errorMessage = "Authentication failed for " + userRole;
            return sendResponseMessage(errorMessage, null,null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();

            // Handle the exception and return an appropriate response
            String errorMessage = "An error occurred during authentication";
            return sendResponseMessage(errorMessage, null,null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    // Separate Method for create the Response
    public ResponseEntity <Map<String, String>> sendResponseMessage (String message, String userId, String returnRole, HttpStatus requestStatus) {
        Map <String, String> response = new HashMap<>();
        response.put("message", message);
        if (returnRole != null) {
            response.put("role", returnRole);
        }
        if (userId != null) {
            response.put("userId", userId);
        }
        response.put("status", String.valueOf(!requestStatus.isError()));
        return new ResponseEntity<>(response, requestStatus);
    }
}
