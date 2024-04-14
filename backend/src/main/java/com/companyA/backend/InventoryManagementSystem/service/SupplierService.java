package com.companyA.backend.InventoryManagementSystem.service;

import com.companyA.backend.InventoryManagementSystem.model.Suppliers;
import com.companyA.backend.InventoryManagementSystem.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public List<Suppliers> supplierDetails(){
        return supplierRepository.findAll();
    }

    public String registerSupplier(Suppliers supplier) {
        supplierRepository.save(supplier); // Save supplier to database
        return "Successfully Registered";
    }

    public void deleteSupplierById(String id) {
        // Check if the repair exists
        if (supplierRepository.existsById(id)) {
            // If the repair exists, delete it
            supplierRepository.deleteById(id);
        } else {
            // If the repair does not exist, throw an exception
            throw new IllegalArgumentException("Repair with ID " + id + " does not exist.");
        }
    }

}