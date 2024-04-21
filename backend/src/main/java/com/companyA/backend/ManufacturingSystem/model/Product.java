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
    @Id                                     // Marks the field as the primary identifier for the document
    private String id;                      // Unique identifier for the product
    private String name;                    // Name of the product
    private String description;             // Description of the product
    private double price;                   // Price of the product
    private int quantity;                   // Quantity of the product
    // Other fields as needed
}
