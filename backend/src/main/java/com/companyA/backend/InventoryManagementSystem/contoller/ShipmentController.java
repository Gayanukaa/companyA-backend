package com.companyA.backend.InventoryManagementSystem.contoller;

import com.companyA.backend.InventoryManagementSystem.model.Shipment;
import com.companyA.backend.InventoryManagementSystem.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/shipments")
public class ShipmentController {

    private final ShipmentService shipmentService;

    @Autowired
    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<Shipment>> getAllShipments() {
        List<Shipment> shipments = shipmentService.getAllShipments();
        return new ResponseEntity<>(shipments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shipment> getShipmentById(@PathVariable String id) {
        Shipment shipment = shipmentService.getShipmentById(id);
        if (shipment != null) {
            return new ResponseEntity<>(shipment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Shipment> createShipment(@RequestBody Shipment shipment) {
        Shipment createdShipment = shipmentService.saveShipment(shipment);
        return new ResponseEntity<>(createdShipment, HttpStatus.CREATED);
    }

    //when creating a shipment should have a map of what is the stock being brought (so stock id and quantity)
    //so have to create new attribute in shipment model to store stock id and quantity. create a map or document reference

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShipment(@PathVariable String id) {
        shipmentService.deleteShipment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}