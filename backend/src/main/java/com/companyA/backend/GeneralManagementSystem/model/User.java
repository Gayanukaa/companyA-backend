package com.companyA.backend.GeneralManagementSystem.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private String password;

}
