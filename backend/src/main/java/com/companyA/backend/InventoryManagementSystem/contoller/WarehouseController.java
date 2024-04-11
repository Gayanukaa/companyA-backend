package com.companyA.backend.InventoryManagementSystem.contoller;

import com.companyA.backend.InventoryManagementSystem.model.Inventory;
import com.companyA.backend.InventoryManagementSystem.model.Stocks;
import com.companyA.backend.InventoryManagementSystem.model.Warehouse;
import com.companyA.backend.InventoryManagementSystem.service.StocksService;
import com.companyA.backend.InventoryManagementSystem.service.WarehouseService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouses() {
        return new ResponseEntity<List<Warehouse>>(warehouseService.allWarehouses(), HttpStatus.OK);
    }

    @PostMapping("/addWarehouse")
    public ResponseEntity<Warehouse> addWarehouse(@RequestBody Warehouse warehouse) {
        return new ResponseEntity<Warehouse>(warehouseService.addWarehouse(warehouse),HttpStatus.CREATED);
    }

    @GetMapping("/getWarehouse/{warehouseId}")
    public ResponseEntity<Warehouse> getWarehouse(@PathVariable String warehouseId) {
        return new ResponseEntity<Warehouse>(warehouseService.getWarehouse(warehouseId),HttpStatus.OK);
    }

}