package com.companyA.backend.InventoryManagementSystem.contoller;

import com.companyA.backend.InventoryManagementSystem.model.StockAlert;
import com.companyA.backend.InventoryManagementSystem.service.StockAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// StockAlertController.java
@RestController
@RequestMapping("/api/v1/stock-alerts")
@CrossOrigin
public class StockAlertController {

    private final StockAlertService stockAlertService;

    //Dependency Injection
    @Autowired
    public StockAlertController(StockAlertService stockAlertService) {
        this.stockAlertService = stockAlertService;
    }

    //View all stock alerts
    @GetMapping
    public ResponseEntity<List<StockAlert>> getAllStockAlerts() {
        return new ResponseEntity<List<StockAlert>>(stockAlertService.allStockAlerts(), HttpStatus.OK);
    }

    //Remove a stock alert by stock alert Id
    @DeleteMapping("/deleteStockAlert/{stockAlertId}")
    public ResponseEntity<String> deleteStockAlert(@PathVariable String stockAlertId) {
        if(stockAlertId == null || stockAlertId.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(stockAlertService.deleteStockAlert(stockAlertId),HttpStatus.OK);
    }

}
