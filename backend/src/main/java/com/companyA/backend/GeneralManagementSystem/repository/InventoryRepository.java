package com.companyA.backend.GeneralManagementSystem.repository;

import com.companyA.backend.GeneralManagementSystem.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.Optional;


public interface InventoryRepository extends MongoRepository<Inventory, String> {
    Optional<Inventory> findById(String id);
    //Optional<Inventory> findById(String id);

    boolean existsById(String id);
    // Add other custom methods for managing managers if needed
}