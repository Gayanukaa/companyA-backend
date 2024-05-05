package com.companyA.backend.InventoryManagementSystem.contoller;

import com.companyA.backend.FinanceSystem.service.FinanceStockAlertService;
import com.companyA.backend.InventoryManagementSystem.DTO.CustomShipmentDTO;
import com.companyA.backend.InventoryManagementSystem.model.Shipment;
import com.companyA.backend.InventoryManagementSystem.DTO.ShipmentAlertDTO;
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

    private final ShipmentService shipmentService;

    private final FinanceStockAlertService financeStockAlertService;

    //Dependency Injection
    @Autowired
    public ShipmentController(ShipmentService shipmentService, FinanceStockAlertService financeStockAlertService) {
        this.shipmentService = shipmentService;
        this.financeStockAlertService = financeStockAlertService;
    }

    //Retrieves all shipments
    @GetMapping("/getShipments")
    public ResponseEntity<List<Shipment>> getAllShipments() {
        List<Shipment> shipments = shipmentService.getAllShipments();
        if (shipments == null || shipments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(shipments, HttpStatus.OK);
        }
    }

    //Retrieve specific shipment details by using the ID
    @GetMapping("/getShipmentsById/{id}")
    public ResponseEntity<Shipment> getShipmentById(@PathVariable String id) {
        Shipment shipment = shipmentService.getShipmentById(id);
        if (shipment != null) {
            return new ResponseEntity<>(shipment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Create a shipment with the stock alerts
    //Interacts with Finance System to confirm payment
    @PostMapping("/createShipment")
    public ResponseEntity<Shipment> createShipment(@RequestBody ShipmentAlertDTO shipmentAlertDTO) {
        try {
            boolean paymentConfirmed = financeStockAlertService.sendRequestForPaymentConfirmation(shipmentAlertDTO.getStockAlerts());
            if (paymentConfirmed) {
                return new ResponseEntity<>(shipmentService.placeShipment(shipmentAlertDTO.getStockAlerts(), shipmentAlertDTO.getInventoryManagerId(), shipmentAlertDTO.getSupplierId()), HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //Place a custom shipment
    @PostMapping("/placeCustomShipment")
    public ResponseEntity<Shipment> placeCustomShipment(@RequestBody CustomShipmentDTO customShipmentDTO) {
        try {
            return new ResponseEntity<>(shipmentService.saveCustomShipment(customShipmentDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    //Delete shipment by its ID
    @DeleteMapping("/deleteShipment/{id}")
    public ResponseEntity<String> deleteShipment(@PathVariable String id) {
        try {
            shipmentService.deleteShipment(id);
            return new ResponseEntity<>("Shipment deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //Receive shipment that has been placed and update the stocks
    @GetMapping("/receiveShipment/{id}")
    public ResponseEntity<List<Stocks>> receiveShipment(@PathVariable String id) {
        try {
            Shipment shipment = shipmentService.getShipmentById(id);
            if (shipment != null) {
                return new ResponseEntity<List<Stocks>>(shipmentService.updateStocks(shipment), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}