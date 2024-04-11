package com.companyA.backend.InventoryManagementSystem.contoller;

import com.companyA.backend.InventoryManagementSystem.model.StateOfProduct;
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
            default:
                return ResponseEntity.badRequest().build(); // Return a 400 status code for unknown attributes
        }
        if (stock != null) {
            return ResponseEntity.ok(stock);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/stateofproduct/{value}")
    public ResponseEntity <List<Stocks>> getStockByStateOfProduct(@PathVariable String value) {
        StateOfProduct valueNew = StateOfProduct.valueOf(value);
        return new ResponseEntity<List<Stocks>>(stocksService.getStockByStateOfProduct(valueNew), HttpStatus.OK);
    }

    @GetMapping("/price/{value}")
    public ResponseEntity <List<Stocks>> getStockByPrice(@PathVariable float value) {
        return new ResponseEntity<List<Stocks>>(stocksService.getStockByPrice(value), HttpStatus.OK);
    }

}