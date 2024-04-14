package com.companyA.backend.ManufacturingSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "OrderStatus")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatus {
    @Id
    private String id;
    private String status;
    private String order_ID;
}