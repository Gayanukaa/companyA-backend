package com.companyA.backend.GeneralManagementSystem.controller;

import com.companyA.backend.GeneralManagementSystem.DTO.LoginDTO;
import com.companyA.backend.GeneralManagementSystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginHandle(@RequestBody LoginDTO loginDTO) {
        return userService.userLogin(loginDTO);
    }
}
