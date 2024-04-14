package com.companyA.backend.InventoryManagementSystem.service;


import com.companyA.backend.InventoryManagementSystem.model.Stocks;
import com.companyA.backend.InventoryManagementSystem.model.Suppliers;
import com.companyA.backend.InventoryManagementSystem.model.Warehouse;
import com.companyA.backend.InventoryManagementSystem.repository.ShipmentRepository;
import com.companyA.backend.InventoryManagementSystem.repository.StocksRepository;
import com.companyA.backend.InventoryManagementSystem.repository.SupplierRepository;
import com.companyA.backend.InventoryManagementSystem.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private StocksRepository stockRepository;

    public void addItemsToInventory(Stocks stock, int quantity, String warehouseId) {
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

