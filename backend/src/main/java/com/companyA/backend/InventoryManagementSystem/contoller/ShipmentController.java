package com.companyA.backend.InventoryManagementSystem.contoller;

import com.companyA.backend.FinanceSystem.service.FinanceSubsystemService;
import com.companyA.backend.InventoryManagementSystem.model.Shipment;
import com.companyA.backend.InventoryManagementSystem.model.StockAlert;
import com.companyA.backend.InventoryManagementSystem.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/shipments")
public class ShipmentController {

    @Autowired
    private final ShipmentService shipmentService;
    @Autowired
    private final FinanceSubsystemService financeSubsystemService;

    @Autowired
    public ShipmentController(ShipmentService shipmentService, FinanceSubsystemService financeSubsystemService) {
        this.shipmentService = shipmentService;
        this.financeSubsystemService = financeSubsystemService;
    }

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
    public ResponseEntity<Shipment> createShipment(@RequestBody List<StockAlert> stockAlerts, @RequestBody String inventoryManagerId, @RequestBody String supplierId) {
        boolean paymentConfirmed = financeSubsystemService.sendRequestForPaymentConfirmation(stockAlerts);
        if (paymentConfirmed) {
            return new ResponseEntity<>(shipmentService.placeShipment(stockAlerts, inventoryManagerId, supplierId), HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //when creating a shipment should have a map of what is the stock being brought (so stock id and quantity)
    //so have to create new attribute in shipment model to store stock id and quantity. create a map or document reference

    @DeleteMapping("/deleteShipment/{id}")
    public ResponseEntity<String> deleteShipment(@PathVariable String id) {
        shipmentService.deleteShipment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}