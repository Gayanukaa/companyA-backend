package com.companyA.backend.FinanceSystem.contoller;

import com.companyA.backend.FinanceSystem.model.SalesRecord;
import com.companyA.backend.FinanceSystem.repository.SalesRecordRepo;
import com.companyA.backend.FinanceSystem.service.IDNotFoundException;
import com.companyA.backend.FinanceSystem.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SalesController {

    @Autowired
    private SalesRecordRepo salesRecordRepo;

    @Autowired
    private SalesService salesService;
    @GetMapping("/bill/{order_ID}")
    public String generate_bill(@PathVariable int order_ID){
        Optional<SalesRecord> salesRecord = salesRecordRepo.findById(order_ID);

        SalesRecord sales = null;

        if(salesRecord.isPresent()){
            sales = salesRecord.get();
        }
        else {
            // Employee not found, throw an exception
            throw new IDNotFoundException("Order ID not found: " + order_ID);
        }
        return salesService.generateBill(sales);
    }

    @PostMapping("/setOrder")
    public void setOrder(@RequestBody SalesRecord salesRecord){
        salesService.addRecord(salesRecord);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleEmployeeNotFoundException(IDNotFoundException ex) {
        // Create a custom response for EmployeeNotFoundException
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
