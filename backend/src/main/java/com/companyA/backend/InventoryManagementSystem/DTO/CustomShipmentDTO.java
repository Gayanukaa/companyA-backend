package com.companyA.backend.InventoryManagementSystem.DTO;

import lombok.Data;
import lombok.Getter;

import java.util.Map;

@Data
@Getter
public class CustomShipmentDTO {
    private Map<String,Integer> orderList;
    private String inventoryManagerId;
    private String supplierId;
}