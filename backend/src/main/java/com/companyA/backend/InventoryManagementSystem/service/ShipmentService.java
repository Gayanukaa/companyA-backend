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
            int id = Integer.parseInt(lastId.substring(1));
            id++;
            shipment.setId("S"+String.format("%04d", id));
        }
        else {
            shipment.setId("S0001");
        }
        shipment.setTrackingNumber("T"+String.format("%04d", new Random().nextInt(10000)));
        shipment.setSender(inventoryManagerRepository.findById(inventoryManagerId).orElse(null));
        shipment.setSuppliers(supplierRepository.findById(supplierId).orElse(null));

        mongoTemplate.update(Suppliers.class)
                .matching(Criteria.where("suppliers").is(shipment.getSuppliers()))
                .apply(new Update().push("orders", shipment.getId())).first();

        Map<Stocks,Integer> orderList = new HashMap<>();
        for (StockAlert stockAlert : stockAlerts) {
            Stocks stock = stocksService.getStockById(stockAlert.getItemId());
            stock.setStateOfProduct(StateOfProduct.valueOf("ORDERED"));
            stocksService.updateStock(stock);
            orderList.put(stock, stock.getReorderQuantity());
        }
        shipment.setOrderList(orderList);
        return shipmentRepository.save(shipment);
    }
}