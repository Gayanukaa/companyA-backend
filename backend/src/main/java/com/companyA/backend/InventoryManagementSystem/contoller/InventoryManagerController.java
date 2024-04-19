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
@RequestMapping("/api/v1/inventoryManager")
public class InventoryManagerController {
    @Autowired
    private InventoryManagerService inventoryManagerService;
    @Autowired
    private GenerateReportService generateReportService;

    //Register an inventory manager
    @PostMapping("/registerManager")
    public ResponseEntity<String> registerInventoryManager(@RequestBody InventoryManager inventoryManager) {
        String message =  inventoryManagerService.registerInventoryManager(inventoryManager);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    //Get details of all managers
    @GetMapping("/details")
    public List<InventoryManager> inventoryManagerDetails(){
        return inventoryManagerService.inventoryManagerDetais();
    }

    //Get details of an inventory manager by manager Id
    @GetMapping("/getInventoryManager/{id}")
    public InventoryManager inventoryManagerDetails(@PathVariable String id){
        return inventoryManagerService.getInventoryManagerById(id);
    }

    //Delete a manager by manager Id
    @DeleteMapping("/deleteManager/{managerId}")
    public ResponseEntity<String> deleteManager(@PathVariable String managerId) {
        return new ResponseEntity<String>(inventoryManagerService.deleteInventoryManagerById(managerId),HttpStatus.OK);
    }

    //Generate a report
    @GetMapping("/generateReport")
    public String getReport(GenerateReport report){
        return generateReportService.createReport(report);
    }

}
