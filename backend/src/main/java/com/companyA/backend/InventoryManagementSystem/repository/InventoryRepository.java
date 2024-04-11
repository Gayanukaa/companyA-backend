package com.companyA.backend.InventoryManagementSystem.repository;

import com.companyA.backend.InventoryManagementSystem.model.Inventory;
import com.companyA.backend.InventoryManagementSystem.model.Stocks;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository <T extends Inventory> extends MongoRepository <T, String> {
}