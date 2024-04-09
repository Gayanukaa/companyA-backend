package com.companyA.backend.GeneralManagementSystem.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;

    @NotBlank(message = "FirstName is Required")
    private String firstName;

    @NotBlank(message = "LastName is Required")
    private String lastName;

    @NotBlank(message = "Mobile Number is Required")
    private String mobileNumber;

    @NotBlank(message = "Email is Required")
    private String email;

    @NotBlank(message = "Password is Required")
    private String password;
}
