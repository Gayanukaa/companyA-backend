package com.companyA.backend.InventoryManagementSystem.service;

import com.companyA.backend.InventoryManagementSystem.model.InventoryType;
import com.companyA.backend.InventoryManagementSystem.model.StateOfProduct;
import com.companyA.backend.InventoryManagementSystem.model.Stocks;
import com.companyA.backend.InventoryManagementSystem.model.Warehouse;
import com.companyA.backend.InventoryManagementSystem.repository.StocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StocksService {


    @Autowired
    private StocksRepository stocksRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Stocks> allStocks() {
        return stocksRepository.findAll();
    }

    public Stocks addStocks(Stocks stocks) {
        mongoTemplate.update(Warehouse.class)
                .matching(Criteria.where("warehouseId").is(stocks.getWarehouseId())).apply(new Update().push("inventoryList").value(stocks.getId())).first();
        return stocksRepository.save(stocks);
    }
}
