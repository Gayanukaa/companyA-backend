package com.companyA.backend.InventoryManagementSystem.model;

import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "inventory")
public abstract class Inventory {

    @Id @NonNull
    private String itemId;
    @DocumentReference
    private Warehouse warehouseId;
    private int quantity;
    private int weight;
    private int size;
    private int reorderQuantity;
    private StateOfProduct stateOfProduct;
}


