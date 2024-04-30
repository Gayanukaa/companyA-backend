package com.companyA.backend.InventoryManagementSystem.model;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "inventory")
public abstract class Inventory {

    @Id
    private String id;
    @NotBlank (message = "Warehouse ID is Required")
    private String warehouseId;
    @NotBlank (message = "Name is Required")
    private String name;
    @NotBlank (message = "Quantity is Required")
    private int quantity;
    @NotBlank (message = "Threshold Quantity is Required")
    private int thresholdQuantity;
    @NotBlank (message = "Weight is Required")
    private int weight;
    @NotBlank (message = "Size is Required")
    private int size;
    @NotBlank (message = "Reorder Quantity is Required")
    private int reorderQuantity;
    @NotBlank (message = "State of Product is Required")
    private StateOfProduct stateOfProduct;
    @NotBlank (message = "Inventory Type is Required")
    private  InventoryType inventoryType;
    @NotBlank (message = "Price is Required")
    private float price;

}
