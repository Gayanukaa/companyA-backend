package com.companyA.backend.InventoryManagementSystem.contoller;

import com.companyA.backend.FinanceSystem.service.FinanceStockAlertService;
import com.companyA.backend.InventoryManagementSystem.model.Shipment;
import com.companyA.backend.InventoryManagementSystem.model.ShipmentRequest;
import com.companyA.backend.InventoryManagementSystem.model.Stocks;
import com.companyA.backend.InventoryManagementSystem.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/shipments")
@CrossOrigin
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;
    @Autowired
    private FinanceStockAlertService financeStockAlertService;

    @GetMapping("/getShipments")
    public ResponseEntity<List<Shipment>> getAllShipments() {
        List<Shipment> shipments = shipmentService.getAllShipments();
        if (shipments == null || shipments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(shipments, HttpStatus.OK);
        }
    }

    @GetMapping("/getShipmentsById/{id}")
    public ResponseEntity<Shipment> getShipmentById(@PathVariable String id) {
        Shipment shipment = shipmentService.getShipmentById(id);
        if (shipment != null) {
            return new ResponseEntity<>(shipment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/createShipment")
    public ResponseEntity<Shipment> createShipment(@RequestBody ShipmentRequest shipmentRequest) {
        boolean paymentConfirmed = financeStockAlertService.sendRequestForPaymentConfirmation(shipmentRequest.getStockAlerts());
        if (paymentConfirmed) {
            return new ResponseEntity<>(shipmentService.placeShipment(shipmentRequest.getStockAlerts(), shipmentRequest.getInventoryManagerId(), shipmentRequest.getSupplierId()), HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteShipment/{id}")
    public ResponseEntity<String> deleteShipment(@PathVariable String id) {
        shipmentService.deleteShipment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Receive shipment
    @GetMapping("/receiveShipment/{id}")
    public ResponseEntity<List<Stocks>> receiveShipment(@PathVariable String id) {
        Shipment shipment = shipmentService.getShipmentById(id);
        if (shipment != null) {
            return new ResponseEntity<List<Stocks>>(shipmentService.updateStocks(shipment), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}