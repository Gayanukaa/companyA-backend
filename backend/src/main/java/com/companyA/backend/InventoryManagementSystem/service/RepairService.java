package com.companyA.backend.InventoryManagementSystem.service;

import com.companyA.backend.InventoryManagementSystem.model.*;
import com.companyA.backend.InventoryManagementSystem.repository.RepairRepository;
import com.companyA.backend.InventoryManagementSystem.repository.StocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepairService {

    @Autowired
    private RepairRepository repairRepository;


    ////////////////////////////////

    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private StocksRepository stocksRepository;

    public List<String> getIdsOfDamagedProducts() {
        return inventoryService.getIdsOfDamagedProducts();
    }


    public void sendItemsForRepair(List<String> itemIds) {
        // Update stateOfProduct to "UNDER_REPAIR" for items in inventory
        //Done

        List<Stocks> itemsToRepair = stocksRepository.findAllById(itemIds);
        for (Stocks item : itemsToRepair) {
            item.setStateOfProduct(StateOfProduct.UNDER_REPAIR);
        }
        System.out.println(itemsToRepair.size());
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

    public void deleteRepairById(String id) {
        // Check if the repair exists
        if (repairRepository.existsById(id)) {
            // If the repair exists, delete it
            repairRepository.deleteById(id);
        } else {
            // If the repair does not exist, throw an exception
            throw new IllegalArgumentException("Repair with ID " + id + " does not exist.");
        }
    }

    public void updateRepairedItems(List<String> itemIds) {
        // Update stateOfProduct to "REPAIRED" for items in inventory
        List<Stocks> itemsToRemove = stocksRepository.findAllById(itemIds);
        for (Stocks item : itemsToRemove) {
            item.setStateOfProduct(StateOfProduct.REPAIRED);
        }
        stocksRepository.saveAll(itemsToRemove);


        //sendForRepairService.removeSelectedItemsFromRepairCollection(itemIds);
    }
    public void saveSelectedItemsToRepairCollection(List<Stocks> itemsToRepair) {
        List<Repair> selectedDataList = new ArrayList<>();
        for (Stocks item : itemsToRepair) {
            // Extract the specific data you want to delete from each Repair item
            Repair selectedData = new Repair();
            selectedData.setInventoryId(item.getId());
            selectedData.setName(item.getName());
            selectedData.setQuantity(item.getQuantity());

            selectedDataList.add(selectedData);
        }
        repairRepository.saveAll(selectedDataList);
    }
    ////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////

        /*public void removeSelectedItemsFromRepairCollection(List <String> idsToRemove) {
            List<String> selectedDataList = new ArrayList<>();
            for (String item : idsToRemove) {
                Repair selectedData = new Repair();
                selectedDataList.add(selectedData.getId());
                System.out.println(selectedData.getInventoryId());
            }
            repairRepository.deleteAllById(selectedDataList);
            //System.out.println(selectedDataList.get(0));

        }*/

}

