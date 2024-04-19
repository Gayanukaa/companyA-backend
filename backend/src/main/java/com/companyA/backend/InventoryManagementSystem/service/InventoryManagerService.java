package com.companyA.backend.InventoryManagementSystem.service;


import com.companyA.backend.InventoryManagementSystem.model.*;
import com.companyA.backend.InventoryManagementSystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InventoryManagerService {

    @Autowired
    private InventoryManagerRepository inventoryManagerRepository;

    //Get specific Inventory Manager by Id
    public InventoryManager getInventoryManagerById(String id) {
        return inventoryManagerRepository.findById(id).orElse(null);
    }

    //Get all of the inventory managers exist
    public List<InventoryManager> inventoryManagerDetais(){
        return inventoryManagerRepository.findAll();
    }

    //Register an inventory manager
    public String registerInventoryManager(InventoryManager inventoryManager) {

        //Generate Id of the inventory manager considering the last manager Id
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
        return "Successfully Registered"; //Return if the inventory manager is successfully registered
    }

    //Delete specific inventory manager by the Id
    public String deleteInventoryManagerById(String managerId) {
        InventoryManager manager = inventoryManagerRepository.findById(managerId).orElse(null);
        if (manager == null) {
            return HttpStatus.NOT_FOUND.toString(); //Return if the specified manager does not exist
        }
        inventoryManagerRepository.deleteById(managerId);
        return HttpStatus.OK.toString(); //Return if the manager deleted
    }

}

