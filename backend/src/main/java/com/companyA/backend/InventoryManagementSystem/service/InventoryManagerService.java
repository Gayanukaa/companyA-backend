package com.companyA.backend.InventoryManagementSystem.service;


import com.companyA.backend.InventoryManagementSystem.model.InventoryManager;
import com.companyA.backend.InventoryManagementSystem.model.Stocks;
import com.companyA.backend.InventoryManagementSystem.model.Suppliers;
import com.companyA.backend.InventoryManagementSystem.model.Warehouse;
import com.companyA.backend.InventoryManagementSystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*Inventory Manager will get stock object and a warehouse id and a quantity then search for the warehouse id and
substract the quantity from the warehouse
 */

@Service
public class InventoryManagerService {

    @Autowired
    private StocksService stocksService;
    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private StocksRepository stockRepository;

    @Autowired
    private InventoryManagerRepository inventoryManagerRepository;

    public InventoryManager getInventoryManagerById(String id) {
        return inventoryManagerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Inventory Manager not found"));
    }

    public List<InventoryManager> inventoryManagerDetails(){
        return inventoryManagerRepository.findAll();
    }

    public String registerInventoryManager(InventoryManager inventoryManager) {
        inventoryManagerRepository.save(inventoryManager);
        return "Successfully Registered";
    }

    public void deleteInventoryManagerById(String id) {
        // Check if the Inventory Manager with ID exists
        if (inventoryManagerRepository.existsById(id)) {
            // If the Inventory Manager with ID exists, delete it
            inventoryManagerRepository.deleteById(id);
        } else {
            // If the Inventory Manager does not exist, throw an exception
            throw new IllegalArgumentException("Inventory Manager with ID " + id + " does not exist.");
        }
    }

    // Method to add items to inventory
    public void addItemsToInventory(String stockId, int quantity) {
        // Retrieve the stock object by its ID
        Stocks stock = stocksService.getStockById(stockId);

        // Check if the stock object exists
        if (stock != null) {
            // Update the stock quantity by adding the provided quantity
            stock.setQuantity(stock.getQuantity() + quantity);

            // Save or update the stock object
            stocksService.addStocks(stock);
        } else {
            // Handle the case where the stock object does not exist
            throw new IllegalArgumentException("Stock with ID " + stockId + " not found");
        }
    }

    // Method to remove items from inventory
    public void removeItemsFromInventory(String stockId, int quantity) {
        // Retrieve the stock object by its ID
        Stocks stock = stocksService.getStockById(stockId);

        // Check if the stock object exists
        if (stock != null) {
            // Check if there is enough stock available to remove
            if (stock.getQuantity() >= quantity) {
                // Update the stock quantity by subtracting the provided quantity
                stock.setQuantity(stock.getQuantity() - quantity);

                // Save or update the stock object
                stocksService.addStocks(stock);
            } else {
                // Handle the case where there is not enough stock available
                throw new IllegalArgumentException("Not enough stock available to remove");
            }
        } else {
            // Handle the case where the stock object does not exist
            throw new IllegalArgumentException("Stock with ID " + stockId + " not found");
        }
    }

    //OLD METHODS NEED TO CHECK AND DELETE
    /*
    public void addItemsToInventory(String stockId,int quantity) {
        // Save or update stock
        stock.setQuantity(stock.getQuantity() + quantity);
        stocksService.addStocks(stock);
    }

    public void removeItemsFromInventory(Stocks stock, int quantity) {
        // Check if there is enough stock to delete
        if (stock.getQuantity() < quantity) {
            throw new IllegalArgumentException("Not enough stock available");
        }

        // Update stock quantity
        stock.setQuantity(stock.getQuantity() - quantity);
        stockRepository.save(stock);
    }
    */
    public Warehouse addWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public String deleteWarehouse(String id) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Warehouse not found"));
        warehouseRepository.deleteById(id);
        return "Warehouse Deleted Successfully";
    }

    public void updateWarehouse(Warehouse warehouse) {

        warehouse = warehouseRepository.findById(warehouse.getWarehouseId())
                .orElseThrow(() -> new IllegalArgumentException("Warehouse not found"));
        warehouseRepository.save(warehouse);
    }

    //Register new supplier
    public String registerSupplier(Suppliers suppliers){
        return supplierService.registerSupplier(suppliers);
    }
}

