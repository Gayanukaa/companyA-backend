package com.companyA.backend.InventoryManagementSystem.service;

import com.companyA.backend.FinanceSystem.service.FinanceSubsystemService;
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

    private final StockAlertRepository stockAlertRepository;

    private final FinanceSubsystemService financeSubsystemService;

    private final ShipmentService shipmentService;

    private final StocksRepository stocksRepository;

    @Autowired
    public StockAlertService(StockAlertRepository stockAlertRepository, FinanceSubsystemService financeSubsystemService, ShipmentService shipmentService, StocksRepository stocksRepository) {
        this.stockAlertRepository = stockAlertRepository;
        this.financeSubsystemService = financeSubsystemService;
        this.shipmentService = shipmentService;
        this.stocksRepository = stocksRepository;
    }

    public List<StockAlert> createStockAlert(List<Stocks> stocks) {
        List<StockAlert> stockalerts = new ArrayList<>();
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
            stockalerts.add(stockAlert);
        }

        return stockalerts;
    }

}
