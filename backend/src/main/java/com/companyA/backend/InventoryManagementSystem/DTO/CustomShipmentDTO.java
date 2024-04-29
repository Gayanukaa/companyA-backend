package com.companyA.backend.InventoryManagementSystem.DTO;

import lombok.Data;
import lombok.Getter;

import java.util.Map;

@Data
@Getter
public class CustomShipmentDTO {
    private String itemId;
    private int requestedQuantity;
    private String inventoryManagerId;
    private String supplierId;
}