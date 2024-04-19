package com.companyA.backend.FinanceSystem.repository;

import com.companyA.backend.FinanceSystem.model.FinanceStockAlert;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StockAlertRepo extends MongoRepository<FinanceStockAlert,String> {
}
