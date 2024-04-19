package com.companyA.backend.ManufacturingSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;

import java.util.List;

// Entity class representing an Order
@Document(collection = "Orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    private ObjectId id;              // Unique identifier for the order
    private String orderId;           // Identifier for the order
    private String lineId;            // Identifier for the assigned production line
    private List<Employee> workers;   // Workers assigned to the production line for this order
}
