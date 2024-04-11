package com.companyA.backend.GeneralManagementSystem.service;


import com.companyA.backend.GeneralManagementSystem.model.Inventory;
import com.companyA.backend.GeneralManagementSystem.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor


public class InventoryService {

    private static InventoryRepository inventoryRepository;
   // private PasswordEncoder passwordEncoder;


    //createInventory method eka static kale, controller eke error rkak nisa
    public static String createInventory(Inventory inventory) {
        String tempId = inventory.getId();
        Optional<Inventory> ExistingInventory = inventoryRepository.findById(tempId);
        //Optional<Inventory> ExistingInventory = inventoryRepository.findById(tempId);


        if (ExistingInventory!=null) {
            Inventory tempName = ExistingInventory.get();
            return "The personnel with the specific name is already assigned to "+tempName+".";
        }
        else {
            //String hashedPassword = passwordEncoder.encode(manager.getPassword());
            //manager.setPassword(hashedPassword);
            inventoryRepository.save(inventory);
            return "Inventory added Successfully";
        }
    }

    public static Optional<Inventory> findInventoryById(String inventoryId) {
        return inventoryRepository.findById(inventoryId);
    }

    public static String updateInventoryById(Inventory inventory) {
        String inventoryId = inventory.getId();
        inventoryRepository.deleteById(inventoryId);
        inventoryRepository.save(inventory);
        return "Inventory updated successfully.";
    }

}
