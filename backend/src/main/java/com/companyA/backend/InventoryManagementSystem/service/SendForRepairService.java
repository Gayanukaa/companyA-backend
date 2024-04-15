package com.companyA.backend.InventoryManagementSystem.service;

import com.companyA.backend.InventoryManagementSystem.model.Repair;
import com.companyA.backend.InventoryManagementSystem.model.SendForRepair;
import com.companyA.backend.InventoryManagementSystem.repository.SendForRepairRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SendForRepairService {

    private final SendForRepairRepository sendForRepairRepository;

    public SendForRepairService(SendForRepairRepository sendForRepairRepository) {
        this.sendForRepairRepository = sendForRepairRepository;
    }

    public void saveSelectedItemsToRepairCollection(List<Repair> itemsToRepair) {
        List<SendForRepair> selectedDataList = new ArrayList<>();
        for (Repair item : itemsToRepair) {
            // Extract the specific data you want to delete from each Repair item
            SendForRepair selectedData = new SendForRepair();
            selectedData.setInventoryId(item.getId());
            selectedData.setName(item.getName());
            selectedData.setQuantity(item.getQuantity());

            selectedDataList.add(selectedData);
        }
        sendForRepairRepository.saveAll(selectedDataList);
    }

    public void removeSelectedItemsFromRepairCollection(List<String> idsToRemove){
        List<String> selectedDataList = new ArrayList<>();
        for (String item : idsToRemove) {
            SendForRepair selectedData = new SendForRepair();
            if (item.equals(selectedData.getInventoryId())){
                selectedDataList.add(selectedData.getId());
            }
            else{
                throw new IllegalArgumentException("Repair with ID " + selectedData.getInventoryId() + " does not exist.");
            }

        }
        sendForRepairRepository.deleteAllById(selectedDataList);
    }
}
