package com.companyA.backend.InventoryManagementSystem.contoller;

import com.companyA.backend.InventoryManagementSystem.model.Stocks;
import com.companyA.backend.InventoryManagementSystem.service.StocksService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/stock")
public class StockController {

    @Autowired
    private StocksService stocksService;

    @GetMapping
    public ResponseEntity<List<Stocks>> getAllStocks() {
        return new ResponseEntity<List<Stocks>>(stocksService.allStocks(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Stocks> addStocktoInventory(@RequestBody Map<String,String> payload) {
        Stocks savedStocks = stocksService.addStocks(payload.get("id"),payload.get("warehouseId"),payload.get("name"),
                payload.get("quantity"),payload.get("weight"),
                payload.get("size"),payload.get("reorderQuantity"),payload.get("stateOfProduct"),
                payload.get("inventoryType"), payload.get("price"));
        return new ResponseEntity<>(savedStocks, HttpStatus.CREATED);

    }
}