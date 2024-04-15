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

    @Id @NotBlank
    private String alertId;
    @NotBlank
    private String itemId;
    @NotBlank
    private String itemName;
    @NotBlank
    private int reorderQuantity;
}