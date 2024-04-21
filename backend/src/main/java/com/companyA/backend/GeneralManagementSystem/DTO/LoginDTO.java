package com.companyA.backend.GeneralManagementSystem.DTO;

import lombok.Getter;

@Getter
public class LoginDTO {

    private String email;
    private String password;
    private String role;
}
