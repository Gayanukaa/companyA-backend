package com.companyA.backend.InventoryManagementSystem.service;

import com.companyA.backend.InventoryManagementSystem.model.*;
import com.companyA.backend.InventoryManagementSystem.repository.GenerateReportRepository;
import com.companyA.backend.InventoryManagementSystem.repository.InventoryRepository;
import com.companyA.backend.InventoryManagementSystem.repository.StocksRepository;
import com.companyA.backend.InventoryManagementSystem.repository.WarehouseRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class GenerateReportService {


    @Autowired
    private StocksRepository stocksRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;



    @Autowired
    private GenerateReportRepository generateReportRepository;

    private List<Stocks> stocksList;

    @PostConstruct
    public void init() {
        stocksList = stocksRepository.findAll();
    }

    public List<GenerateReport> reportDetails(){
        return generateReportRepository.findAll();
    }

    public String createReport(GenerateReport report) {
        if(!generateReportRepository.findAll().isEmpty()) {
            String lastId = generateReportRepository.findAll().get(generateReportRepository.findAll().size()-1).getReportId();
            int id = Integer.parseInt(lastId.substring(1));
            id++;
            report.setReportId("R"+String.format("%04d", id));
        }
        else {
            report.setReportId("R0001");
        }
        report.setTotalWorth(this.findWorth());
        report.setMostRemainingItemsByWarehouse(this.mostQuantity());
        report.setWarehouseItemsByWarehouse(this.findItemsInWarehouse());
        report.setWarehouses(this.findWarehouses());
        //report.setGeneratedDateAndTime(LocalDateTime.now());
        // Set the generated date and time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = now.format(formatter);
        report.setGeneratedDateAndTime(formattedDateTime);
        generateReportRepository.save(report); // Save report to database

        return "Report Generated Successfully";
    }

    public String deleteReportById(String reportId) {
        GenerateReport report = generateReportRepository.findById(reportId).orElse(null);
        if (report == null) {
            return HttpStatus.NOT_FOUND.toString();
        }
        generateReportRepository.deleteById(reportId);
        return HttpStatus.OK.toString();
    }

    public GenerateReport getReportById(String id) {
        return generateReportRepository.findById(id).orElse(null);
    }


    //Find which item has the most quantity in each warehouse
    private Map<String, List<String>> mostQuantity() {
        //List<Stocks> stocksList = stocksRepository.findAll();

        // Create a map to store most sold items by warehouse
        Map<String, List<String>> mostRemainingItemsByWarehouse = new HashMap<>();

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
                mostRemainingItemsByWarehouse.computeIfAbsent(entry.getKey(), k -> new ArrayList<>())
                        .add(bestSellerItem.getName());
            }
        }

        return mostRemainingItemsByWarehouse;
    }


    //Find the total worth of stock in each warehouse

    private Map<String, Double> findWorth() {
        //List<Inventory> inventoryList = inventoryRepository.findAll();
        Map<String, Double> warehouseWorth = new HashMap<>();

        for (Stocks stocks : stocksList) {
            String warehouseId = stocks.getWarehouseId();
            double totalItemRevenue = stocks.getPrice() * stocks.getQuantity();
            warehouseWorth.merge(warehouseId, totalItemRevenue, Double::sum);
        }

        return warehouseWorth;
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




}
