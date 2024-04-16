package com.companyA.backend.InventoryManagementSystem.contoller;

import com.companyA.backend.InventoryManagementSystem.model.*;
import com.companyA.backend.InventoryManagementSystem.service.InventoryManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventoryManager")
public class InventoryManagerController {
    @Autowired
    private InventoryManagerService inventoryManagerService;

    @PostMapping("/registerManager")
    public ResponseEntity<String> registerInventoryManager(@RequestBody InventoryManager inventoryManager) {
        String message =  inventoryManagerService.registerInventoryManager(inventoryManager);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping("/details")
        public List<InventoryManager> inventoryManagerDetails(){
        return inventoryManagerService.inventoryManagerDetais();
    }


    @DeleteMapping("/deleteManager/{id}")
    public ResponseEntity<String> deleteInventoryManagerById(@PathVariable String id) {
        try {
            inventoryManagerService.deleteInventoryManagerById(id);
            return ResponseEntity.ok("Inventory Manager deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
/*
    @PostMapping("/addItemsToInventory")
    public void addItemsToInventory(@RequestParam String stockId, @RequestParam int quantity) {
        inventoryManagerService.addItemsToInventory(stockId, quantity);
    }

    @PostMapping("/removeItemsFromInventory")
    public void removeItemsFromInventory(@RequestParam String stockId, @RequestParam int quantity) {
        inventoryManagerService.removeItemsFromInventory(stockId, quantity);
    }

    //change these below by invoking methods of warehouse service

    @PostMapping("/addWarehouse")
    public Warehouse addWarehouse(@RequestBody Warehouse warehouse) {
        return inventoryManagerService.addWarehouse(warehouse);
    }

    @PostMapping("/deleteWarehouse")
    public String deleteWarehouse(@RequestParam String id) {
        return inventoryManagerService.deleteWarehouse(id);
    }

    @PostMapping("/updateWarehouse")
    public void updateWarehouse(@RequestBody Warehouse warehouse) {
        inventoryManagerService.updateWarehouse(warehouse);
    }
    */
}
