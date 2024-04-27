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

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping("/registerSupplier")
    public ResponseEntity<Suppliers> registerSupplier(@RequestBody Suppliers supplier) {
        return new ResponseEntity<Suppliers>(supplierService.registerSupplier(supplier),HttpStatus.CREATED);
    }

    @GetMapping("/supplierDetails")
    public List<Suppliers> supplierDetails(){
        return supplierService.supplierDetails();
    }

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

