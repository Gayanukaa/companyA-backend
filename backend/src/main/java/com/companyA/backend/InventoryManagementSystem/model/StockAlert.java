package com.companyA.backend.InventoryManagementSystem.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "StockAlert")
public class StockAlert {

    @Id
    private String alertId;
    @NotBlank (message = "Item ID cannot be blank")
    private String itemId;
    @NotBlank (message = "Item name cannot be blank")
    private String itemName;
    @NotBlank (message = "Item Reorder Quantity cannot be blank")
    private int reorderQuantity;

}