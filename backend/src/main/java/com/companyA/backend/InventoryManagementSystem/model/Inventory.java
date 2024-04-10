package com.companyA.backend.InventoryManagementSystem.model;

import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "inventory")
public abstract class Inventory {

    @Id @NotBlank
    private ObjectId itemId;
    @DocumentReference @NotBlank
    private Warehouse warehouseId;
    @NotBlank
    private int quantity;
    @NotBlank
    private int weight;
    @NotBlank
    private int size;
    @NotBlank
    private int reorderQuantity;
    @NotBlank
    private StateOfProduct stateOfProduct;
    @NotBlank
    private InventoryType inventoryType;
}



