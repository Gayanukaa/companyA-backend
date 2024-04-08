package com.companyA.backend.HumanResourceSystem.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "inventory")
public abstract class Inventory {

    @Id
    private String itemId;
    private String warehouseId;
    private int quantity;
    private String status;
    private int weight;
    private int size;
    private int reorderQuantity;
    private StateOfProduct stateOfProduct;
}


