package com.companyA.backend.InventoryManagementSystem.service;

import com.companyA.backend.InventoryManagementSystem.model.*;
import com.companyA.backend.InventoryManagementSystem.repository.RepairRepository;
import com.companyA.backend.InventoryManagementSystem.repository.SendForRepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepairService {

    @Autowired
    private RepairRepository repairRepository;


    ////////////////////////////////
    @Autowired
    private SendForRepairRepository sendForRepairRepository;

    @Autowired
    private SendForRepairService sendForRepairService;

    @Autowired
    private InventoryService inventoryService;

    public List<String> getIdsOfDamagedProducts() {
        return inventoryService.getIdsOfDamagedProducts();
    }


    ///////////////////////////////
    //////////////////////////////

    public void sendItemsForRepair(List<String> itemIds) {
        // Update stateOfProduct to "UNDER_REPAIR" for items in inventory
        //Done
        List<Repair> itemsToRepair = repairRepository.findAllById(itemIds);
        for (Repair item : itemsToRepair) {
            item.setStateOfProduct(StateOfProduct.UNDER_REPAIR);
        }
        repairRepository.saveAll(itemsToRepair);

        // Save selected data from itemsToRepair to repair collection
        sendForRepairService.saveSelectedItemsToRepairCollection(itemsToRepair);

    }
    //////////////////////////////////////

    ////////////////////////////////////////

    ////////////////////////////////////////
    public List<SendForRepair> repairDetails(List<String> ids) {
        List<SendForRepair> repairs = sendForRepairRepository.findAllById(ids);

        // Check if all IDs were found
        List<String> foundIds = repairs.stream().map(SendForRepair::getId).collect(Collectors.toList());
        List<String> missingIds = ids.stream().filter(id -> !foundIds.contains(id)).collect(Collectors.toList());

        // If any IDs are missing, throw an IllegalArgumentException with the error message
        if (!missingIds.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return repairs;
    }

    public String sendForRepairs(SendForRepair sendForRepair) {
        sendForRepairRepository.save(sendForRepair); // Save supplier to database
        return "Successfully Registered";
    }

    public void deleteRepairById(String id) {
        // Check if the repair exists
        if (sendForRepairRepository.existsById(id)) {
            // If the repair exists, delete it
            sendForRepairRepository.deleteById(id);
        } else {
            // If the repair does not exist, throw an exception
            throw new IllegalArgumentException("Repair with ID " + id + " does not exist.");
        }
    }

    ////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////
    public void updateRepairedItems(List<String> itemIds) {
        // Update stateOfProduct to "REPAIRED" for items in inventory
        List<Repair> itemsToRemove = repairRepository.findAllById(itemIds);
        for (Repair item : itemsToRemove) {
            item.setStateOfProduct(StateOfProduct.REPAIRED);
        }
        repairRepository.saveAll(itemsToRemove);

        //sendForRepairService.removeSelectedItemsFromRepairCollection(itemIds);
    }
}


