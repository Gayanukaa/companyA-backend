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
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping("/registerSupplier")
    public ResponseEntity<String> registerSupplier(@RequestBody Suppliers supplier) {
        String sup =  supplierService.registerSupplier(supplier);
        return ResponseEntity.status(HttpStatus.OK).body(sup);
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

