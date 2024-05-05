package com.companyA.backend.InventoryManagementSystem.contoller;

import com.companyA.backend.InventoryManagementSystem.model.Warehouse;
import com.companyA.backend.InventoryManagementSystem.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/warehouse")
@CrossOrigin
public class WarehouseController {

    private final WarehouseService warehouseService;

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouses() {
        return new ResponseEntity<List<Warehouse>>(warehouseService.allWarehouses(), HttpStatus.OK);
    }

    //Add a new warehouse
    @PostMapping("/addWarehouse")
    public ResponseEntity<Warehouse> addWarehouse(@RequestBody Warehouse warehouse) {
        try {
            return new ResponseEntity<Warehouse>(warehouseService.addWarehouse(warehouse),HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //Retrieve details of a specific warehouse by using its ID
    @GetMapping("/getWarehouse/{warehouseId}")
    public ResponseEntity<Warehouse> getWarehouse(@PathVariable String warehouseId) {
        if (warehouseId == null || warehouseId.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Warehouse>(warehouseService.getWarehouse(warehouseId),HttpStatus.OK);
    }

    //Remove a warehouse
    @DeleteMapping("/deleteWarehouse/{warehouseId}")
    public ResponseEntity<String> deleteWarehouse(@PathVariable String warehouseId) {
        try {
            return new ResponseEntity<String>(warehouseService.deleteWarehouse(warehouseId),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}