package com.companyA.backend.InventoryManagementSystem.DTO;

import com.companyA.backend.InventoryManagementSystem.model.StockAlert;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
public class ShipmentAlertDTO {
    private List<StockAlert> stockAlerts;
    private String inventoryManagerId;
    private String supplierId;
}
