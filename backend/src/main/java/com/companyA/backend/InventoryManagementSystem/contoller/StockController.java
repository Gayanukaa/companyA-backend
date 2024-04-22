package com.companyA.backend.InventoryManagementSystem.contoller;

import com.companyA.backend.InventoryManagementSystem.model.StateOfProduct;
import com.companyA.backend.InventoryManagementSystem.model.StockAlert;
import com.companyA.backend.InventoryManagementSystem.model.Stocks;
import com.companyA.backend.InventoryManagementSystem.service.StockAlertService;
import com.companyA.backend.InventoryManagementSystem.service.StocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stock")
@CrossOrigin
public class StockController{

    private final StocksService stocksService;

    private final StockAlertService stockAlertService;

    @Autowired
    public StockController(StocksService stocksService, StockAlertService stockAlertService) {
        this.stocksService = stocksService;
        this.stockAlertService = stockAlertService;
    }

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

    @GetMapping("/{attribute}/{value}")
    public ResponseEntity<Stocks> getStockByAttribute(@PathVariable String attribute, @PathVariable String value) {
        Stocks stock = null;
        switch (attribute.toLowerCase()) {
            case "id":
                stock = stocksService.getStockById(value);
                break;
            case "name":
                stock = stocksService.getStockByName(value);
                break;
            case "quantity":
                stock = stocksService.getStockByQuantity(Integer.parseInt(value));
            default:
                return ResponseEntity.badRequest().build(); // Return a 400 status code for unknown attributes
        }
        if (stock != null) {
            return ResponseEntity.ok(stock);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/{attribute}/{value}")
    public ResponseEntity<Stocks> updateStockByAttribute(@PathVariable String id, @PathVariable String attribute, @PathVariable String value) {
        Stocks stock = stocksService.getStockById(id);
        if (stock != null) {
            stocksService.updateStockByAttribute(stock, attribute, value);
            return ResponseEntity.ok(stock);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/stateOfproduct/{value}")
    public ResponseEntity <List<Stocks>> getStockByStateOfProduct(@PathVariable String value) {
        StateOfProduct valueNew = StateOfProduct.valueOf(value);
        return new ResponseEntity<List<Stocks>>(stocksService.getStockByStateOfProduct(valueNew), HttpStatus.OK);
    }

    @GetMapping("/price/{value}")
    public ResponseEntity <List<Stocks>> getStockByPrice(@PathVariable float value) {
        return new ResponseEntity<List<Stocks>>(stocksService.getStockByPrice(value), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/updateStock")
    public void update(@RequestBody Stocks stock) {
        if(!stocksService.existsById(stock.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found!");
        }
        stocksService.updateStock(stock);
    }

    @PostMapping("/checkQuantity")
    public ResponseEntity<List<StockAlert>> checkStockAndProcessAlerts() {
        return new ResponseEntity<List<StockAlert>>(stockAlertService.checkStockAndProcessAlerts(), HttpStatus.OK);
    }

}