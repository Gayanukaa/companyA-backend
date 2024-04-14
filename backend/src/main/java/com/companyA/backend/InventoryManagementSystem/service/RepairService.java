package com.companyA.backend.InventoryManagementSystem.service;

import com.companyA.backend.InventoryManagementSystem.model.Repair;
import com.companyA.backend.InventoryManagementSystem.repository.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepairService {

    @Autowired
    private RepairRepository repairRepository;

    /*public List<Repair> repairDetails() {
        return repairRepository.findAll();
    }*/

    //add method in controller and service to check for damaged stock
    //that means search in inventory for stock with StateOfProduct "DAMAGED"
    //then return the list of stock with StateOfProduct "DAMAGED"
    // so that the user can select which stock to send for repair

    public List<Repair> repairDetails(List<String> ids) {
        List<Repair> repairs = repairRepository.findAllById(ids);

        // Check if all IDs were found
        List<String> foundIds = repairs.stream().map(Repair::getId).toList();
        List<String> missingIds = ids.stream().filter(id -> !foundIds.contains(id)).toList();

        // If any IDs are missing, throw an IllegalArgumentException with the error message
        if (!missingIds.isEmpty()) {
            throw new IllegalArgumentException("wrong product id");
        }

        return repairs;
    }

    public String sendForRepairs(Repair repair) {
        repairRepository.save(repair); // Save supplier to database
        //set stock StateOfProduct to "UNDER_REPAIR"
        return "Successfully Registered";
    }

    //add method in controller and service for after repair is done set stock StateOfProduct to "REPAIRED"
    //check what other info you can give when repair is done

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
}

