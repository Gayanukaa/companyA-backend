package com.companyA.backend.ManufacturingSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product{
    @Id                             // Marks the field as the primary identifier for the document
    private String id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    // Other fields as needed
}
