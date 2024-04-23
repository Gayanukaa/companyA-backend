package com.companyA.backend.InventoryManagementSystem.contoller;

import com.companyA.backend.InventoryManagementSystem.model.StateOfProduct;
import com.companyA.backend.InventoryManagementSystem.model.StockAlert;
import com.companyA.backend.InventoryManagementSystem.model.Stocks;
import com.companyA.backend.InventoryManagementSystem.model.Supplies;
import com.companyA.backend.InventoryManagementSystem.service.StockAlertService;
import com.companyA.backend.InventoryManagementSystem.service.StocksService;
import com.companyA.backend.InventoryManagementSystem.service.SuppliesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
@CrossOrigin
public class InventoryController {

    private final StocksService stocksService;

    private final SuppliesService suppliesService;

    private final StockAlertService stockAlertService;

    //Dependency Injection
    @Autowired
    public InventoryController(StocksService stocksService, SuppliesService suppliesService, StockAlertService stockAlertService) {
        this.stocksService = stocksService;
        this.suppliesService = suppliesService;
        this.stockAlertService = stockAlertService;
    }

    @GetMapping
    public ResponseEntity<List<Stocks>> getAllStocks() {
        return new ResponseEntity<List<Stocks>>(stocksService.allStocks(), HttpStatus.OK);
    }

    @PostMapping("/addStock")
    public ResponseEntity<Stocks> addToInventory(@RequestBody Stocks stocks) {
        return new ResponseEntity<Stocks>(stocksService.addStocks(stocks),HttpStatus.CREATED);
    }

    @PostMapping("/addSupplies")
    public ResponseEntity<Supplies> addToInventory(@RequestBody Supplies supplies) {
        return new ResponseEntity<Supplies>(suppliesService.addSupplies(supplies),HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteInventory/{itemId}")
    public ResponseEntity<String> deleteFromInventory(@PathVariable String itemId) {
        if(stocksService.existsById(itemId)){
            return new ResponseEntity<String>(stocksService.deleteStock(itemId),HttpStatus.OK);
        }
        else if(suppliesService.existsById(itemId)){
            return new ResponseEntity<String>(suppliesService.deleteSupplies(itemId),HttpStatus.OK);
        }
        return new ResponseEntity<String>(HttpStatus.NOT_FOUND.toString(),HttpStatus.NOT_FOUND);
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

    @PutMapping("/stocks/{stockId}/{attribute}/{value}")
    public ResponseEntity<Stocks> updateStockByAttribute(@PathVariable String stockId, @PathVariable String attribute, @PathVariable String value) {
        //find if stock or supplies
        Stocks stock = stocksService.getStockById(stockId);
        if (stock != null) {
            stocksService.updateStockByAttribute(stock, attribute, value);
            return ResponseEntity.ok(stock);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/supplies/{suppliesId}/{attribute}/{value}")
    public ResponseEntity<Supplies> updateSuppliesByAttribute(@PathVariable String suppliesId, @PathVariable String attribute, @PathVariable String value) {
        Supplies supplies = suppliesService.getSuppliesById(suppliesId);
        if (supplies != null) {
            suppliesService.updateSuppliesByAttribute(supplies, attribute, value);
            return ResponseEntity.ok(supplies);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/stateOfProduct/{value}")
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

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/updateSupplies")
    public void update(@RequestBody Supplies supplies) {
        if(!suppliesService.existsById(supplies.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found!");
        }
        suppliesService.updateSupplies(supplies);
    }

    @PostMapping("/checkQuantity")
    public ResponseEntity<List<StockAlert>> checkStockAndProcessAlerts() {
        return new ResponseEntity<List<StockAlert>>(stockAlertService.checkStockAndProcessAlerts(), HttpStatus.OK);
    }

}