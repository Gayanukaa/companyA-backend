package com.companyA.backend.ManufacturingSystem.model;

import com.companyA.backend.InventoryManagementSystem.model.InventoryType;
import com.companyA.backend.InventoryManagementSystem.model.StateOfProduct;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "inventory")
public class MaterialRequest {

    @Id
    private String id;                     // Unique identifier for the material request
    private String warehouseId;            // Identifier for the warehouse
    private String name;                   // Name of the material
    private int quantity;                  // Quantity of the material
    private int thresholdQuantity;         // Name of the material
    private int weight;                    // Weight of the material
    private int size;                      // Size of the material
    private int reorderQuantity;           // Reorder quantity for the material
    private StateOfProduct stateOfProduct; // State of the product
    private InventoryType inventoryType;   // Type of inventory
}