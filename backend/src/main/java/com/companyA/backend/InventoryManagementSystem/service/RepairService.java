package com.companyA.backend.InventoryManagementSystem.service;

import com.companyA.backend.InventoryManagementSystem.model.*;
import com.companyA.backend.InventoryManagementSystem.repository.RepairRepository;
import com.companyA.backend.InventoryManagementSystem.repository.StocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RepairService {

    @Autowired
    private RepairRepository repairRepository;
    @Autowired
    private StocksRepository stocksRepository;

    public List<String> getIdsOfDamagedProducts() {
        List<Stocks> damagedStock = stocksRepository.findByStateOfProduct(StateOfProduct.DAMAGED);
        List<String> damagedProductIds = new ArrayList<>();
        for (Stocks stock : damagedStock) {
            damagedProductIds.add(stock.getId());
        }
        return damagedProductIds;
    }

    public void sendItemsForRepair(List<String> itemIds) {
        // Update stateOfProduct to "UNDER_REPAIR" for items in inventory
        //Done

        List<Stocks> itemsToRepair = stocksRepository.findAllById(itemIds);
        if (itemsToRepair.size() == itemIds.size()){
            for (Stocks item : itemsToRepair) {
                if (item.getStateOfProduct().equals(StateOfProduct.DAMAGED)) {
                    item.setStateOfProduct(StateOfProduct.UNDER_REPAIR);
                } else {
                    throw new IllegalArgumentException();
                }
            }
        }
        else{
            throw new IllegalArgumentException();
        }
        stocksRepository.saveAll(itemsToRepair);

        // Save selected data from itemsToRepair to repair collection
        saveSelectedItemsToRepairCollection(itemsToRepair);
    }


    public List<Repair> repairDetails(List<String> ids) {
        List<Repair> repairs = repairRepository.findAllById(ids);

        // Check if all IDs were found
        List<String> foundIds = repairs.stream().map(Repair::getId).collect(Collectors.toList());
        List<String> missingIds = ids.stream().filter(id -> !foundIds.contains(id)).collect(Collectors.toList());

        // If any IDs are missing, throw an IllegalArgumentException with the error message
        if (!missingIds.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return repairs;
    }

    public String sendForRepairs(Repair repair) {
        repairRepository.save(repair); // Save supplier to database
        return "Successfully Registered";
    }

    //Commented out this scenario due to the complexity.
    //This one is working correctly

    /*public void deleteRepairById(String id) {
        // Check if the repair exists
        if (repairRepository.existsById(id)) {
            // If the repair exists, delete it.Additionally, update the stateOfProduct back to "DAMAGED"(Because the item isn't repaired.But it has removed from repairs)
            Optional<Repair> repairOptional = repairRepository.findById(id);
            String inventoryId = repairOptional.map(Repair::getInventoryId).orElse(null);
            Optional<Stocks> itemsToRepair = stocksRepository.findById(inventoryId);
            Stocks modifiedRepair = itemsToRepair.map(repair -> {
                repair.setStateOfProduct(StateOfProduct.DAMAGED);
                return repair;
            }).orElse(null);
            stocksRepository.save(modifiedRepair);
            repairRepository.deleteById(id);

        } else {
            // If the repair does not exist, throw an exception
            throw new IllegalArgumentException("Repair with ID " + id + " does not exist.");
        }
    }
    */

    //You should give a list of repairIds-----Remember
    public void updateRepairedItems(List<String> itemIds) {
        //repairRepository.deleteAllByIdIn(itemIds);
        // Update stateOfProduct to "REPAIRED" for items in inventory
        List<Repair> itemsToRemove = repairRepository.findAllById(itemIds);
        List<String> inventoryIds = new ArrayList<>();

        if (itemsToRemove.size() == itemIds.size()) {
            for (Repair item : itemsToRemove) {
                inventoryIds.add(item.getInventoryId());
            }
            List<Stocks> idsToRemove = stocksRepository.findAllById(inventoryIds);
            for (Stocks item : idsToRemove) {
                item.setStateOfProduct(StateOfProduct.REPAIRED);
            }
            stocksRepository.saveAll(idsToRemove);

            for (String itemId : itemIds) {
                repairRepository.deleteById(itemId);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void saveSelectedItemsToRepairCollection(List<Stocks> itemsToRepair) {
        List<Repair> selectedDataList = new ArrayList<>();
        int lastId = repairRepository.findAll().size();
        for (Stocks item : itemsToRepair) {
            // Extract the specific data you want to delete from each Repair item
            lastId += 1;
            Repair selectedData = new Repair();
            selectedData.setInventoryId(item.getId());
            selectedData.setName(item.getName());
            selectedData.setQuantity(item.getQuantity());
            selectedData.setId("R"+String.format("%04d", lastId));
            selectedDataList.add(selectedData);
        }
        repairRepository.saveAll(selectedDataList);
    }

}



