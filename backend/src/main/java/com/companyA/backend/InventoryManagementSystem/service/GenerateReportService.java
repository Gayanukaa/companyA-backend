package com.companyA.backend.InventoryManagementSystem.service;

import com.companyA.backend.InventoryManagementSystem.model.*;
import com.companyA.backend.InventoryManagementSystem.repository.GenerateReportRepository;
import com.companyA.backend.InventoryManagementSystem.repository.InventoryRepository;
import com.companyA.backend.InventoryManagementSystem.repository.StocksRepository;
import com.companyA.backend.InventoryManagementSystem.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class GenerateReportService {

    /*
    @Autowired
    private StocksRepository stocksRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

     */

    @Autowired
    private GenerateReportRepository generateReportRepository;
    //private List<Stocks> stocksList = stocksRepository.findAll();

    //NEED TO CHECK
    /*
    public GenerateReport generateReport() {
        Map<String, List<String>> mostSoldItemsByWarehouse = mostSold();
        //Map<String, Double> revenue = findRevenue(); //Should update when price attribute is added
        LocalDateTime generatedDateAndTime = LocalDateTime.now();
        Map<String, String> warehouse = findWarehouses();

        GenerateReport report = new GenerateReport();
        report.setReportId("SomeGeneratedId");
        report.setGeneratedDateAndTime(generatedDateAndTime);
        report.setMostSoldItemsByWarehouse(mostSoldItemsByWarehouse);
        //report.setRevenue(revenue);
        report.setWarehouses(warehouse);

        generateReportRepository.save(report);
        return null;
    }

    */
    public List<GenerateReport> reportDetails(){
        return generateReportRepository.findAll();
    }

    public String createReport(GenerateReport report) {
        generateReportRepository.save(report); // Save report to database
        return "Successfully Generated";
    }

    public void deleteReportId(String id) {
        // Check if the report exists
        if (generateReportRepository.existsById(id)) {
            //If so delete the report
            generateReportRepository.deleteById(id);
        } else {
            //If the report does not exist
            throw new IllegalArgumentException("Report with ID " + id + " does not exist.");
        }
    }

    /*
    //Find which item has the most quantity in each warehouse
    private Map<String, List<String>> mostQuantity() {
        //List<Stocks> stocksList = stocksRepository.findAll();

        // Create a map to store most sold items by warehouse
        Map<String, List<String>> mostSoldItemsByWarehouse = new HashMap<>();

        // Group inventory items by warehouse ID
        Map<String, List<Stocks>> inventoryByWarehouse = new HashMap<>();
        for (Stocks stocks : stocksList) {
            inventoryByWarehouse.computeIfAbsent(stocks.getWarehouseId(), k -> new ArrayList<>()).add(stocks);
        }

        // Find the most sold item for each warehouse
        for (Map.Entry<String, List<Stocks>> entry : inventoryByWarehouse.entrySet()) {
            List<Stocks> warehouseInventory = entry.getValue();
            Inventory bestSellerItem = warehouseInventory.stream()
                    .max(Comparator.comparingInt(Inventory::getQuantity))
                    .orElse(null);
            if (bestSellerItem != null) {
                mostSoldItemsByWarehouse.computeIfAbsent(entry.getKey(), k -> new ArrayList<>())
                        .add(bestSellerItem.getName());
            }
        }

        return mostSoldItemsByWarehouse;
    }


    //Find the total worth of stock in each warehouse

    private Map<String, Double> findRevenue() {
        //List<Inventory> inventoryList = inventoryRepository.findAll();
        Map<String, Double> warehouseRevenue = new HashMap<>();

        for (Stocks stocks : stocksList) {
            String warehouseId = stocks.getWarehouseId();
            double totalItemRevenue = stocks.getPrice() * stocks.getQuantity();
            warehouseRevenue.merge(warehouseId, totalItemRevenue, Double::sum);
        }

        return warehouseRevenue;
    }


    //Find what are the items each warehouse contains
    private Map<String, Map<String, String>> findItemsInWarehouse() {
        //List<Inventory> inventoryList = inventoryRepository.findAll();
        Map<String, Map<String, String>> itemsInWarehouse = new HashMap<>();

        for (Stocks stocks : stocksList) {
            String warehouseId = stocks.getWarehouseId();
            Map<String, String> inventoryDetails = itemsInWarehouse.computeIfAbsent(warehouseId, k -> new HashMap<>());
            inventoryDetails.put(stocks.getId(), stocks.getName());
        }

        return itemsInWarehouse;
    }
    //Find what are the available warehouses
    private Map<String, String> findWarehouses() {
        List<Warehouse> warehouses = warehouseRepository.findAll();
        Map<String, String> warehousesMap = new HashMap<>();

        for (Warehouse warehouse : warehouses) {
            warehousesMap.put(warehouse.getWarehouseId(), warehouse.getName());
        }

        return warehousesMap;
    }


    */

}
