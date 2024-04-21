package com.companyA.backend.SalesSystem.contoller;

import com.companyA.backend.SalesSystem.model.CustomerData;
import com.companyA.backend.SalesSystem.model.SalesRecord;
import com.companyA.backend.SalesSystem.service.SalesTableService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/SalesTable")
public class SalesTableController {

    @Autowired
    private SalesTableService salesTableService;

    @GetMapping
    public ResponseEntity<List<CustomerData>> getAllRecords(){
        System.out.println("In thr java all product controller");
        return new ResponseEntity<List<CustomerData>>(salesTableService.allRecords(), HttpStatus.OK);
    }

    @PostMapping("/addRecord")
    public ResponseEntity<CustomerData> createOrder(@RequestBody CustomerData orderDocument) {
        CustomerData savedOrder = salesTableService.saveOrder(orderDocument);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/add")
    public ResponseEntity<CustomerData> addOrder(@PathVariable ObjectId id, @RequestBody SalesRecord newOrder) {
        CustomerData updatedDocument = salesTableService.addOrder(id, newOrder);
        if (updatedDocument != null) {
            return new ResponseEntity<>(updatedDocument, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Document not found
        }
    }
}
