package com.companyA.backend.InventoryManagementSystem.repository;

import com.companyA.backend.InventoryManagementSystem.model.Stocks;
import com.companyA.backend.InventoryManagementSystem.model.Supplies;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SuppliesRepository extends InventoryRepository<Supplies>{

}