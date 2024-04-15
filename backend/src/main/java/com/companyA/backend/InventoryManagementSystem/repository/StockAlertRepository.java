package com.companyA.backend.InventoryManagementSystem.repository;

import com.companyA.backend.InventoryManagementSystem.model.StockAlert;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockAlertRepository extends MongoRepository<StockAlert, String> {
    StockAlert findByItemId(String id);
    // Add custom repository methods if needed
}