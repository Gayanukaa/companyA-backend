package com.companyA.backend.FinanceSystem.repository;

import com.companyA.backend.InventoryManagementSystem.model.StockAlert;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StockAlertRepo extends MongoRepository<StockAlert,String> {
}
