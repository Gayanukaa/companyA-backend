package com.companyA.backend.InventoryManagementSystem.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Suppliers")
public class Suppliers {
    @Id
    private String supplierId;
    @NotBlank (message = "Company name cannot be blank")
    private String companyName;
    @NotBlank (message = "Contact information cannot be blank")
    private String contactInfo;
    @NotBlank (message = "Manager Id cannot be blank")
    private String managerId;
}
