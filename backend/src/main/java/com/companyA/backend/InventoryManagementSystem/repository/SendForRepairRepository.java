package com.companyA.backend.InventoryManagementSystem.repository;

import com.companyA.backend.InventoryManagementSystem.model.Repair;
import com.companyA.backend.InventoryManagementSystem.model.SendForRepair;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SendForRepairRepository extends MongoRepository<SendForRepair, String> {

}
