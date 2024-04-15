package com.companyA.backend.InventoryManagementSystem.model;

import java.util.List;
import java.util.Map;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Suppliers")

public class Suppliers {
    @Id @NotBlank
    private String supplierId;
    @NotBlank
    private String companyName;
    @NotBlank
    private String contactInfo;
    @DocumentReference
    private List<Shipment> orders;
}
