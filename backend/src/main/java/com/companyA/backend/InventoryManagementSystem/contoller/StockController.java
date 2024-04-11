package com.companyA.backend.InventoryManagementSystem.contoller;

import com.companyA.backend.InventoryManagementSystem.model.Stocks;
import com.companyA.backend.InventoryManagementSystem.service.StocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/stock")
public class StockController{

    @Autowired
    private StocksService stocksService;

    @GetMapping
    public ResponseEntity<List<Stocks>> getAllStocks() {
        return new ResponseEntity<List<Stocks>>(stocksService.allStocks(), HttpStatus.OK);
    }

    @PostMapping("/addStock")
    public ResponseEntity<Stocks> addStockInventory(@RequestBody Stocks stocks) {
        return new ResponseEntity<Stocks>(stocksService.addStocks(stocks),HttpStatus.CREATED);
    }

    //Delete stock by id
    @DeleteMapping("/deleteStock/{stockId}")
    public ResponseEntity<String> deleteStock(@PathVariable String stockId) {
        return new ResponseEntity<String>(stocksService.deleteStock(stockId),HttpStatus.OK);
    }
}