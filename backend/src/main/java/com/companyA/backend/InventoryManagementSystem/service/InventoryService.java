package com.companyA.backend.InventoryManagementSystem.service;
import com.companyA.backend.InventoryManagementSystem.model.StateOfProduct;
import com.companyA.backend.InventoryManagementSystem.model.Stocks;
import com.companyA.backend.InventoryManagementSystem.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository<Stocks> inventoryRepository;

    public InventoryService(InventoryRepository<Stocks> inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }
    ////////////////////////////////////////
    public List<String> getIdsOfDamagedProducts() {
        List<Stocks> damagedStock = inventoryRepository.findByStateOfProduct(StateOfProduct.DAMAGED);
        List<String> damagedProductIds = new ArrayList<>();
        for (Stocks stock : damagedStock) {
            damagedProductIds.add(stock.getId());
        }
        return damagedProductIds;
    }
}
