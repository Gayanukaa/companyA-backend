package com.companyA.backend.InventoryManagementSystem.contoller;

import com.companyA.backend.InventoryManagementSystem.model.Stocks;
import com.companyA.backend.InventoryManagementSystem.model.Warehouse;
import com.companyA.backend.InventoryManagementSystem.service.StocksService;
import com.companyA.backend.InventoryManagementSystem.service.WarehouseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stock")
public class StockController {

    private StocksService stockService;



    @PostMapping
    public ResponseEntity<Stocks> addStocktoInventory(@RequestBody @Valid Stocks stocks) {
        Stocks savedStocks = stockService.addStocks(stocks);
        return new ResponseEntity<>(savedStocks, HttpStatus.CREATED);
    }
}