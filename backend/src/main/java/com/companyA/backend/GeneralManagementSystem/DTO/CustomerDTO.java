package com.companyA.backend.GeneralManagementSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CustomerDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
}
