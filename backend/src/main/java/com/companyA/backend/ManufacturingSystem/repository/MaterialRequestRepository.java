package com.companyA.backend.ManufacturingSystem.repository;

import com.companyA.backend.InventoryManagementSystem.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRequestRepository extends MongoRepository<Inventory, String> {
}