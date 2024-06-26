package com.companyA.backend.CustomerOrderSystem.contoller;
import com.companyA.backend.CustomerOrderSystem.model.GetQuotation;
import com.companyA.backend.CustomerOrderSystem.service.GetQuotationService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController // Girlss....this annotation indicates that this class is a REST controller
@RequestMapping("/api/v1")  //  Girlss...this annotation specifies that all endpoints mapped in this class will start with /api/v1.
public class GetQuotationController {
    @Autowired
    private GetQuotationService getQuotationService;  // Autowired Dependency Injection

    @GetMapping("/getQuotation")
    public ResponseEntity<Optional<GetQuotation>> getQuotation(
            @RequestParam("quotation_ID") ObjectId quotation_ID) {

        Optional<GetQuotation> response = getQuotationService.getQuotation(quotation_ID);
        return new ResponseEntity<Optional<GetQuotation>>(response, HttpStatus.OK);
    }
}