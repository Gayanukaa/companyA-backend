package com.companyA.backend.InventoryManagementSystem.contoller;

import com.companyA.backend.InventoryManagementSystem.model.*;
import com.companyA.backend.InventoryManagementSystem.service.GenerateReportService;
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
    @Autowired
    private GenerateReportService generateReportService;

    @PostMapping("/registerManager")
    public ResponseEntity<String> registerInventoryManager(@RequestBody InventoryManager inventoryManager) {
        String message =  inventoryManagerService.registerInventoryManager(inventoryManager);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping("/details")
    public List<InventoryManager> inventoryManagerDetails(){
        return inventoryManagerService.inventoryManagerDetais();
    }

    @GetMapping("/getInventoryManager/{id}")
    public InventoryManager inventoryManagerDetails(@PathVariable String id){
        return inventoryManagerService.getInventoryManagerById(id);
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
    @GetMapping("/getReport")
    public String getReport(GenerateReport report){
        return generateReportService.createReport(report);
    }

}
