package com.companyA.backend.GeneralManagementSystem.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Contactus {
    @NotBlank(message = "Name is Required")
    private String name;

    @NotBlank(message = "Email is Required")
    private String email;

    @NotBlank(message = "Messege is Required")
    private String message;
}
