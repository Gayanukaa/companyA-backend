package com.companyA.backend.InventoryManagementSystem.service;


import com.companyA.backend.InventoryManagementSystem.model.*;
import com.companyA.backend.InventoryManagementSystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InventoryManagerService {

    @Autowired
    private InventoryManagerRepository inventoryManagerRepository;

    public InventoryManager getInventoryManagerById(String id) {
        return inventoryManagerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Inventory Manager not found"));
    }

    public List<InventoryManager> inventoryManagerDetais(){
        return inventoryManagerRepository.findAll();
    }


    public String registerInventoryManager(InventoryManager inventoryManager) {
        if(!inventoryManagerRepository.findAll().isEmpty()) {
            String lastId = inventoryManagerRepository.findAll().get(inventoryManagerRepository.findAll().size()-1).getManagerId();
            int id = Integer.parseInt(lastId.substring(1));
            id++;
            inventoryManager.setManagerId("M"+String.format("%04d", id));
        }
        else {
            inventoryManager.setManagerId("M0001");
        }
        inventoryManagerRepository.save(inventoryManager);
        return "Successfully Registered";
    }



    public void deleteInventoryManagerById(String id) {
        // Check if the Inventory Manager with ID exists
        if (inventoryManagerRepository.existsById(id)) {
            // If the Inventory Manager with ID exists, delete it
            inventoryManagerRepository.deleteById(id);
        } else {
            // If the Inventory Manager does not exist, throw an exception
            throw new IllegalArgumentException("Inventory Manager with ID " + id + " does not exist.");
        }
    }

}

