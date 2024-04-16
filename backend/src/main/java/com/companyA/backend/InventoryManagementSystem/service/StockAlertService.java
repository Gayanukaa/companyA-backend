package com.companyA.backend.InventoryManagementSystem.service;

//import com.companyA.backend.FinanceSystem.service.FinanceSubsystemService;
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

    /* @Autowired
    private FinanceSubsystemService financeSubsystemService;

    @Autowired
    private ShipmentService shipmentService;*/

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
            if (stockAlertRepository.findByItemId(stock.getId()) != null) {
                continue;
            }
            int reorderQuantity = stock.getThresholdQuantity() - stock.getQuantity();
            StockAlert stockAlert = new StockAlert();
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
}
