package com.companyA.backend.InventoryManagementSystem.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Shipments")
public class Shipment {

    @Id @NotBlank
    private String id;
    @NotBlank
    private String trackingNumber;
    @DocumentReference @NotBlank
    private InventoryManager sender;
    @DocumentReference @NotBlank
    private Suppliers supplierId;
    @NotBlank
    private Map<String,Integer> orderList;
}