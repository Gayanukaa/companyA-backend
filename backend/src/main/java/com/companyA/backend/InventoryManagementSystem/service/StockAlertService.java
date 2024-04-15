package com.companyA.backend.InventoryManagementSystem.service;

import com.companyA.backend.InventoryManagementSystem.model.StockAlert;
import com.companyA.backend.InventoryManagementSystem.repository.StockAlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockAlertService {

    @Autowired
    private StockAlertRepository stockAlertRepository;

    public void createStockAlert(String itemId, String itemName, int quantity, int threshold) {
        int reorderQuantity = threshold - quantity;
        StockAlert stockAlert = new StockAlert();
        stockAlert.setItemId(itemId);
        stockAlert.setItemName(itemName);
        stockAlert.setReorderQuantity(reorderQuantity);
        stockAlertRepository.save(stockAlert);
    }
}
