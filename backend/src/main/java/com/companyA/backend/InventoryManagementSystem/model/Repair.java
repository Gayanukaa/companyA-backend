package com.companyA.backend.InventoryManagementSystem.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
//@AllArgsConstructor
@Document(collection = "inventory")
public class Repair{
    //@Id
    //private String id;
    //private int quantityToRepair;
    //private String name;
    /////////////////////////////////////////////
    @Id @NotBlank
    private String id;
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
    @NotBlank
    private float price;
}

