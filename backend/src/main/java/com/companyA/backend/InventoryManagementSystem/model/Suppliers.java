package com.companyA.backend.InventoryManagementSystem.model;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

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
