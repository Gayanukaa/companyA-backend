package com.companyA.backend.GeneralManagementSystem.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Requests")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Requests {
    @NotBlank(message = "Name is Required")
    private String name;

    @NotBlank(message = "Email is Required")
    private String email;

    @NotBlank(message = "Type is Required")
    private String type;

    @NotBlank(message = "Message is Required")
    private String message;

    private int status;
}
