package com.companyA.backend.InventoryManagementSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ShipmentRequest {
    private List<StockAlert> stockAlerts;
    private String inventoryManagerId;
    private String supplierId;

    // Getters and setters
}
