package com.companyA.backend.InventoryManagementSystem.service;

import com.companyA.backend.InventoryManagementSystem.model.Stocks;
import com.companyA.backend.InventoryManagementSystem.repository.StocksRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class StocksService {


    @Autowired
    private StocksRepository stocksRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Stocks addStocks(@Valid Stocks stocks) {
        return stocksRepository.save(stocks);
    }
}
