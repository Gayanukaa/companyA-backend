package com.companyA.backend.InventoryManagementSystem.service;

import com.companyA.backend.InventoryManagementSystem.model.Warehouse;
import com.companyA.backend.InventoryManagementSystem.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    @Autowired
    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public List<Warehouse> allWarehouses() {
        return warehouseRepository.findAll();
    }

    public Warehouse addWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public Warehouse getWarehouse(String warehouseId) {
        return warehouseRepository.findById(warehouseId).orElse(null);
    }

    public String deleteWarehouse(String warehouseId) {
        warehouseRepository.deleteById(warehouseId);
        return "Warehouse with id " + warehouseId + " deleted successfully";
    }
}
