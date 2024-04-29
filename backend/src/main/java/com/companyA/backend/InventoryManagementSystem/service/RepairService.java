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

    public List<Stocks> getDamagedProducts() {
        return stocksRepository.findByStateOfProduct(StateOfProduct.DAMAGED);
    }

    public void sendItemsForRepair(String itemIds) {
        // Update stateOfProduct to "UNDER_REPAIR" for items in inventory
        //Done

        Optional<Stocks> itemsToRepair = stocksRepository.findById(itemIds);
        if(itemsToRepair.isPresent()) {
            Stocks stock = itemsToRepair.get();
            String state = String.valueOf(stock.getStateOfProduct());

            if (state.equals("DAMAGED")) {
                // The state is "DAMAGED"
                System.out.println("The state is DAMAGED");
                stock.setStateOfProduct(StateOfProduct.UNDER_REPAIR);
                stocksRepository.save(stock);

            } else {
                // The state is not "DAMAGED"
                throw new IllegalArgumentException();
            }
        }
        else{
            throw new IllegalArgumentException();
        }

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

    public List<Repair> getAllRepairs() {
        return repairRepository.findAll();
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
    public void updateRepairedItems(String itemId) {
        //repairRepository.deleteAllByIdIn(itemIds);
        // Update stateOfProduct to "REPAIRED" for items in inventory
        Optional<Repair> itemsToRemove = repairRepository.findById(itemId);
        if (itemsToRemove.isPresent()) {
            String id = String.valueOf(itemsToRemove.get().getInventoryId());
            Optional<Stocks> item = stocksRepository.findById(id);
            String state = String.valueOf(item.get().getStateOfProduct());

            if (state.equals("UNDER_REPAIR")) {
                item.get().setStateOfProduct(StateOfProduct.REPAIRED);
                stocksRepository.save(item.get());
                repairRepository.deleteById(itemId);

            } else {
                throw new IllegalArgumentException();
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void saveSelectedItemsToRepairCollection(Optional<Stocks> itemsToRepair) {
        Repair repair = new Repair();
        repair.setInventoryId(itemsToRepair.get().getId());
        repair.setName(itemsToRepair.get().getName());
        repair.setQuantity(itemsToRepair.get().getQuantity());
        repair.setId("R" + String.format("%04d", repairRepository.findAll().size() + 1));
        repairRepository.save(repair);
    }

}



