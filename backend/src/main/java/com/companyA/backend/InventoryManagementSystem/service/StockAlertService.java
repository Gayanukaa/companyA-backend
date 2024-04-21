package com.companyA.backend.InventoryManagementSystem.service;

import com.companyA.backend.InventoryManagementSystem.model.StateOfProduct;
import com.companyA.backend.InventoryManagementSystem.model.StockAlert;
import com.companyA.backend.InventoryManagementSystem.model.Stocks;
import com.companyA.backend.InventoryManagementSystem.repository.StockAlertRepository;
import com.companyA.backend.InventoryManagementSystem.repository.StocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockAlertService {

    @Autowired
    private StockAlertRepository stockAlertRepository;

    @Autowired
    private StocksRepository stocksRepository;


    public List<StockAlert> checkStockAndProcessAlerts() {
        List<Stocks> stocks = stocksRepository.findAllByQuantityLessThanThresholdQuantity();
        List<Stocks> orderStocks = stocks.stream().filter(stock -> stock.getQuantity() < stock.getThresholdQuantity()).toList();
        return createStockAlert(orderStocks);
    }

    public List<StockAlert> createStockAlert(List<Stocks> stocks) {
        List<StockAlert> stockAlerts = new ArrayList<>();
        for (Stocks stock : stocks) {
            stock.setStateOfProduct(StateOfProduct.valueOf("LOW_STOCK"));
            int reorderQuantity = stock.getThresholdQuantity() - stock.getQuantity();
            StockAlert stockAlert = new StockAlert();
            if(!stockAlertRepository.findAll().isEmpty()) {
                String lastId = stockAlertRepository.findAll().get(stockAlertRepository.findAll().size()-1).getAlertId();
                int id = Integer.parseInt(lastId.substring(1));
                id++;
                stockAlert.setAlertId("A"+String.format("%04d", id));
            }
            else {
                stockAlert.setAlertId("A0001");
            }
            stockAlert.setItemId(stock.getId());
            stockAlert.setItemName(stock.getName());
            stockAlert.setReorderQuantity(reorderQuantity);
            stockAlertRepository.save(stockAlert);
            stock.setReorderQuantity(reorderQuantity);
            stocksRepository.save(stock);
            stockAlerts.add(stockAlert);
        }

        return stockAlerts;
    }

    public StockAlert getStockAlertByItemId(String key) {
        return stockAlertRepository.findByItemId(key);
    }

    public String deleteStockAlert(String alertId) {
        stockAlertRepository.deleteById(alertId);
        return "Stock Alert with id " + alertId + " deleted successfully";
    }

    public List<StockAlert> allStockAlerts () {
        return stockAlertRepository.findAll();
    }
}
