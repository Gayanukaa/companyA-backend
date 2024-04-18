package com.companyA.backend.SalesSystem.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "inventory")
public class Product {

    @Id
    private String id;
    private double price;
    private String warehouseId;
    private String name;
    private int quantity;
    private int thresholdQuantity;
    private int weight;
    private int size;
    private int reorderQuantity;
    private String stateOfProduct;
    private String inventoryType;
}
