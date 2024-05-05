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

    //Retrieves details of all stocks available in the inventory
    @GetMapping
    public ResponseEntity<List<Stocks>> getAllStocks() {
        return new ResponseEntity<List<Stocks>>(stocksService.allStocks(), HttpStatus.OK);
    }

    //Add new stock
    @PostMapping("/addStock")
    public ResponseEntity<Stocks> addToInventory(@RequestBody Stocks stocks) {
        try {
            return new ResponseEntity<Stocks>(stocksService.addStocks(stocks),HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //Add new supplies
    @PostMapping("/addSupplies")
        public ResponseEntity<Supplies> addToInventory(@RequestBody Supplies supplies) {
        try {
            return new ResponseEntity<Supplies>(suppliesService.addSupplies(supplies),HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //Remove an inventory item
    @DeleteMapping("/deleteInventory/{itemId}")
    public ResponseEntity<String> deleteFromInventory(@PathVariable String itemId) {
        try {
            if(stocksService.existsById(itemId)){
                return new ResponseEntity<String>(stocksService.deleteStock(itemId),HttpStatus.OK);
            }
            else{
                return new ResponseEntity<String>("Stock not found",HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //Retrieves all inventory for specific value in attributes
    @GetMapping("/{attribute}/{value}")
    public ResponseEntity<Stocks> getStockByAttribute(@PathVariable String attribute, @PathVariable String value) {
        Stocks stock = null;
        try {
            switch (attribute.toLowerCase()) {
                case "id":
                    stock = stocksService.getStockById(value);
                    break;
                case "name":
                    stock = stocksService.getStockByName(value);
                    break;
                default:
                    return ResponseEntity.badRequest().build(); // Return a 400 status code for unknown attributes
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (stock != null) {
            return ResponseEntity.ok(stock);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Updates a specific inventory item based on the provided id, updating attribute and new value.
    @PutMapping("/stocks/{stockId}/{attribute}/{value}")
    public ResponseEntity<Stocks> updateStockByAttribute(@PathVariable String stockId, @PathVariable String attribute, @PathVariable String value) {
        Stocks stock = stocksService.getStockById(stockId);
        if (stock != null) {
            stocksService.updateStockByAttribute(stock, attribute, value);
            return ResponseEntity.ok(stock);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Gets all inventory based on the provided state of product
    @GetMapping("/stateOfProduct/{value}")
    public ResponseEntity <List<Stocks>> getStockByStateOfProduct(@PathVariable String value) {
        try {
            StateOfProduct valueNew = StateOfProduct.valueOf(value);
            return new ResponseEntity<List<Stocks>>(stocksService.getStockByStateOfProduct(valueNew), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Updates the stock
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/updateStock")
    public void update(@RequestBody Stocks stock) {
        try {
            if(!stocksService.existsById(stock.getId())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found!");
            }
            stocksService.updateStock(stock);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid input!");
        }
    }

    // Checks the stock and processes alerts for quantity less than threshold
    @PostMapping("/checkQuantity")
    public ResponseEntity<List<StockAlert>> checkStockAndProcessAlerts() {
        try {
            return new ResponseEntity<List<StockAlert>>(stockAlertService.checkStockAndProcessAlerts(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}