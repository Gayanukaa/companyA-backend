package com.companyA.backend.ManufacturingSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Order status")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatus {
    @Id
    private ObjectId id;    // Unique identifier for the order status
    private String status;  // Current status of the order
    private String orderID; // Unique identifier for the order
}