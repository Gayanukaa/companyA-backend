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

    @Autowired
    private StockAlertService stockAlertService;

    //view all stock alerts
    @GetMapping
    public ResponseEntity<List<StockAlert>> getAllStockAlerts() {
        return new ResponseEntity<List<StockAlert>>(stockAlertService.allStockAlerts(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteStockAlert/{stockAlertId}")
    public ResponseEntity<String> deleteStockAlert(@PathVariable String stockAlertId) {
        return new ResponseEntity<String>(stockAlertService.deleteStockAlert(stockAlertId),HttpStatus.OK);
    }

}
