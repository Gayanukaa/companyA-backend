package com.companyA.backend.InventoryManagementSystem.contoller;

import com.companyA.backend.InventoryManagementSystem.service.StockAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// StockAlertController.java
@RestController
@RequestMapping("/api/v1/stock-alerts")
public class StockAlertController {

    private final StockAlertService stockAlertService;

    @Autowired
    public StockAlertController(StockAlertService stockAlertService) {
        this.stockAlertService = stockAlertService;
    }

}
