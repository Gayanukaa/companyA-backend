package com.companyA.backend.CustomerOrderSystem.contoller;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.companyA.backend.CustomerOrderSystem.model.GetQuotation;
import com.companyA.backend.CustomerOrderSystem.model.Login;
import com.companyA.backend.CustomerOrderSystem.model.SalesRecord;
import com.companyA.backend.CustomerOrderSystem.service.GetQuotationService;
import com.companyA.backend.CustomerOrderSystem.service.LoginService;
import com.companyA.backend.CustomerOrderSystem.service.SalesRecordService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class SalesRecordController {
    @Autowired
    private SalesRecordService salesRecordService;

    @GetMapping("/salesRecord")
    public ResponseEntity<Optional<SalesRecord>> getOrderHistory(@RequestParam("user_ID") ObjectId user_ID) {
        Optional<SalesRecord> response = salesRecordService.getOrderHistory(user_ID);
        return new ResponseEntity<Optional<SalesRecord>>(response, HttpStatus.OK);
    }
}


