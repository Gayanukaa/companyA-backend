package com.companyA.backend.InventoryManagementSystem.service;

import com.companyA.backend.InventoryManagementSystem.model.*;
import com.companyA.backend.InventoryManagementSystem.repository.InventoryManagerRepository;
import com.companyA.backend.InventoryManagementSystem.repository.ShipmentRepository;
import com.companyA.backend.InventoryManagementSystem.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.util.*;

@Service
public class ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private InventoryManagerRepository inventoryManagerRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private StocksService stocksService;

    private MongoTemplate mongoTemplate;
    @Autowired
    private StockAlertService stockAlertService;

    public List<Shipment> getAllShipments() {
        return shipmentRepository.findAll();
    }

    public Shipment getShipmentById(String id) {
        return shipmentRepository.findById(id).orElse(null);
    }

    public Shipment saveShipment(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }

    public void deleteShipment(String id) {
        shipmentRepository.deleteById(id);
    }

    public Shipment placeShipment(List<StockAlert> stockAlerts, String inventoryManagerId, String supplierId) {
        Shipment shipment = new Shipment();
        if(!shipmentRepository.findAll().isEmpty()) {
            String lastId = shipmentRepository.findAll().get(shipmentRepository.findAll().size()-1).getId();
            int id = Integer.parseInt(lastId.substring(4));
            id++;
            shipment.setId("SHIP"+String.format("%04d", id));
        }
        else {
            shipment.setId("SHIP0001");
        }
        shipment.setTrackingNumber("T"+String.format("%04d", new Random().nextInt(10000)));
        shipment.setSender(inventoryManagerRepository.findById(inventoryManagerId).orElse(null));
        shipment.setSupplierId(supplierRepository.findById(supplierId).orElse(null));

        Map<String,Integer> orderList = new HashMap<>();
        for (StockAlert stockAlert : stockAlerts) {
            Stocks stock = stocksService.getStockById(stockAlert.getItemId());
            stock.setStateOfProduct(StateOfProduct.valueOf("ORDERED"));
            stocksService.updateStock(stock);
            orderList.put(stock.getId(), stock.getReorderQuantity());
        }
        shipment.setOrderList(orderList);
        return shipmentRepository.save(shipment);
    }

    public List<Stocks> updateStocks(Shipment shipment) {
        List<Stocks> stocks = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : shipment.getOrderList().entrySet()) {
            Stocks stock = stocksService.getStockById(entry.getKey());
            stock.setQuantity(stock.getQuantity() + entry.getValue());
            stock.setStateOfProduct(StateOfProduct.valueOf("IN_STOCK"));
            StockAlert stockAlert = stockAlertService.getStockAlertByItemId(entry.getKey());
            stockAlertService.deleteStockAlert(stockAlert.getAlertId());
            stocksService.updateStock(stock);
            stocks.add(stock);
        }
        return stocks;
    }
}
