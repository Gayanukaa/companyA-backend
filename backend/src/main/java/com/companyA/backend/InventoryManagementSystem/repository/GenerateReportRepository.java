package com.companyA.backend.InventoryManagementSystem.repository;

import com.companyA.backend.InventoryManagementSystem.model.GenerateReport;
import com.companyA.backend.InventoryManagementSystem.model.Stocks;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenerateReportRepository extends MongoRepository<GenerateReport, String> {

}