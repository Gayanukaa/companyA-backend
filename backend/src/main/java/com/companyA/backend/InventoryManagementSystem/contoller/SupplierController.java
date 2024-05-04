package com.companyA.backend.InventoryManagementSystem.contoller;

import com.companyA.backend.InventoryManagementSystem.model.Suppliers;
import com.companyA.backend.InventoryManagementSystem.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/suppliers")
@CrossOrigin
public class SupplierController {

    private final SupplierService supplierService;

    //Dependency Injection
    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    //Register a new supplier
    @PostMapping("/registerSupplier")
    public ResponseEntity<Suppliers> registerSupplier(@RequestBody Suppliers supplier) {
        try {
            return new ResponseEntity<Suppliers>(supplierService.registerSupplier(supplier),HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //Retrieve all supplier details
    @GetMapping("/supplierDetails")
    public List<Suppliers> supplierDetails(){
        return supplierService.supplierDetails();
    }

    //Remove a supplier by using its ID
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteSupplierById(@PathVariable String id) {
        try {
            supplierService.deleteSupplierById(id);
            return ResponseEntity.ok("Supplier deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

