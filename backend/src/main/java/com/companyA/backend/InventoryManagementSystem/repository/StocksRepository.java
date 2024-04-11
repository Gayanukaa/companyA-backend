package com.companyA.backend.InventoryManagementSystem.repository;

import com.companyA.backend.InventoryManagementSystem.model.Stocks;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StocksRepository extends InventoryRepository<Stocks>{
    List<Stocks> findByPrice(float price);
}