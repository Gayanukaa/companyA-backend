package com.companyA.backend.FinanceSystem.contoller;

import com.companyA.backend.FinanceSystem.model.GenerateSalesBill;
import com.companyA.backend.FinanceSystem.repository.GenerateSalesBillRepo;
import com.companyA.backend.FinanceSystem.service.IDNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class GenerateSalesBillController {

    @Autowired
    private GenerateSalesBillRepo generateSalesBillRepo;


    @GetMapping("/getOrderHistory")
    public List<GenerateSalesBill> orderHistory(){

        return generateSalesBillRepo.findAll();

    }
    @GetMapping("/bill/{order_ID}")
    public GenerateSalesBill generate_bill(@PathVariable String order_ID){
        Optional<GenerateSalesBill> salesRecord = generateSalesBillRepo.findById(order_ID);

        GenerateSalesBill sales = null;

        if(salesRecord.isPresent()){
            sales = salesRecord.get();
        }
        else {
            // Employee not found, throw an exception
            throw new IDNotFoundException("Order ID not found: " + order_ID);
        }
        return sales;
    }


    @ExceptionHandler
    public ResponseEntity<String> handleEmployeeNotFoundException(IDNotFoundException ex) {
        // Create a custom response for EmployeeNotFoundException
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
