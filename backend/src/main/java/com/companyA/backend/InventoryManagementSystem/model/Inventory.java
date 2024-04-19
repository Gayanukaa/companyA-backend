package com.companyA.backend.InventoryManagementSystem.model;

import jakarta.validation.constraints.NotBlank;
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
    private String id;
    @NotBlank
    private String warehouseId;
    @NotBlank
    private String name;
    @NotBlank
    private int quantity;
    @NotBlank
    private int thresholdQuantity;
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

/*
id
warehouseId
name
quantity
weight
size
reorderQuantity
stateOfProduct
inventoryType
*/

