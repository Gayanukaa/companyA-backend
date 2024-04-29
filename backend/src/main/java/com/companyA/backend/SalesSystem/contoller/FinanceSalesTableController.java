package com.companyA.backend.SalesSystem.contoller;

import com.companyA.backend.SalesSystem.model.CustomerData;
import com.companyA.backend.SalesSystem.model.CustomerDataFinance;
import com.companyA.backend.SalesSystem.model.FinanceSalesTableBody;
import com.companyA.backend.SalesSystem.model.SalesRecord;
import com.companyA.backend.SalesSystem.service.FinanceSalesTableService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/financeSalesTable")
@CrossOrigin
public class FinanceSalesTableController {

    @Autowired
    private FinanceSalesTableService financeSalesTableService;

    @GetMapping("/generateID")
    public ResponseEntity<String> generateID(){
        System.out.println("In thr java all product controller");
        return new ResponseEntity<String>(financeSalesTableService.generateID(), HttpStatus.OK);
    }

    @PostMapping("/addRecord")
    public ResponseEntity<FinanceSalesTableBody> createOrder(@RequestBody CustomerDataFinance orderDocument) {
        FinanceSalesTableBody newOrder = financeSalesTableService.preProcessing(orderDocument);
        FinanceSalesTableBody savedOrder = financeSalesTableService.saveOrder(newOrder);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

}
