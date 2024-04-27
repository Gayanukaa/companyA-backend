package com.companyA.backend.InventoryManagementSystem.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Shipments")
public class Shipment {

    @Id
    private String id;
    @NotBlank (message = "Tracking number cannot be blank")
    private String trackingNumber;
    @DocumentReference @NotBlank (message = "Sender cannot be blank")
    private InventoryManager sender;
    @DocumentReference @NotBlank (message = "Supplier cannot be blank")
    private Suppliers supplierId;
    @NotBlank (message = "Shipment orders cannot be blank")
    private Map<String,Integer> orderList;
}