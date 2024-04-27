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
    @NotBlank
    private String companyName;
    @NotBlank
    private String contactInfo;
    @NotBlank
    private String managerId;
}
