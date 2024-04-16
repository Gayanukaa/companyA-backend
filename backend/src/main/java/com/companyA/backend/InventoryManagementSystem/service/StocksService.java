package com.companyA.backend.InventoryManagementSystem.service;

import com.companyA.backend.InventoryManagementSystem.model.*;
import com.companyA.backend.InventoryManagementSystem.repository.StocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StocksService{

    @Autowired
    private StocksRepository stocksRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Stocks> allStocks() {
        return stocksRepository.findAll();
    }

    public Stocks addStocks(Stocks stocks) {
        if(!stocksRepository.findAll().isEmpty()) {
            String lastId = stocksRepository.findAll().get(stocksRepository.findAll().size()-1).getId();
            int id = Integer.parseInt(lastId.substring(1));
            id++;
            stocks.setId("I"+String.format("%04d", id));
        }
        else {
            stocks.setId("S0001");
        }
        Query query = new Query(Criteria.where("warehouseId").is(stocks.getWarehouseId()).and("inventoryList.0").is(null));
        boolean hasNullAtFirstIndex = mongoTemplate.exists(query, Warehouse.class);

        if (hasNullAtFirstIndex) {
            Update update = new Update().set("inventoryList.0", stocks.getId());
            mongoTemplate.updateFirst(query, update, Warehouse.class);
        } else {
            mongoTemplate.update(Warehouse.class)
                    .matching(Criteria.where("warehouseId").is(stocks.getWarehouseId()))
                    .apply(new Update().push("inventoryList", stocks.getId())).first();
        }

        return stocksRepository.save(stocks);
    }

    public String deleteStock(String stockId) {
        Stocks stocks = stocksRepository.findById(stockId).orElse(null);
        if (stocks == null) {
            return HttpStatus.NOT_FOUND.toString();
        }
        mongoTemplate.update(Warehouse.class)
                .matching(Criteria.where("warehouseId").is(stocks.getWarehouseId())).apply(new Update().pull("inventoryList", stocks.getId())).first();
        stocksRepository.deleteById(stockId);
        return HttpStatus.OK.toString();
    }

    public Stocks getStockById(String id) {
        return stocksRepository.findById(id).orElse(null);
    }

    public Stocks getStockByName(String name) {
        return stocksRepository.findByName(name);
    }

    public Stocks getStockByQuantity(int i) {
        return stocksRepository.findByQuantity(i);
    }

    public List<Stocks> getStockByStateOfProduct(StateOfProduct stateOfProduct) {
        return stocksRepository.findByStateOfProduct(stateOfProduct);
    }

    public List<Stocks> getStockByPrice(float price) {
        return stocksRepository.findByPrice(price);
    }

    public boolean existsById(String id) {
        return stocksRepository.existsById(id);
    }

    public void updateStock(Stocks stock) {
        stocksRepository.save(stock);
    }

    public void updateStockByAttribute(Stocks stock, String attribute, String value) {
        switch (attribute) {
            case "name":
                stock.setName(value);
                break;
            case "quantity":
                stock.setQuantity(Integer.parseInt(value));
                break;
            case "weight":
                stock.setWeight(Integer.parseInt(value));
                break;
            case "size":
                stock.setSize(Integer.parseInt(value));
                break;
            case "reorderQuantity":
                stock.setReorderQuantity(Integer.parseInt(value));
                break;
            case "stateOfProduct":
                stock.setStateOfProduct(StateOfProduct.valueOf(value));
                break;
            case "inventoryType":
                stock.setInventoryType(InventoryType.valueOf(value));
                break;
        }
        stocksRepository.save(stock);
    }
}
