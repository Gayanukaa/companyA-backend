package com.companyA.backend.FinanceSystem.contoller;


import com.companyA.backend.FinanceSystem.model.CreditFacility;
import com.companyA.backend.FinanceSystem.repository.CreditFacilityRepo;
import com.companyA.backend.FinanceSystem.service.CreditFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CreditFacilityController {
    @Autowired
    private CreditFacilityService creditFacilityService;

    @Autowired
    private CreditFacilityRepo creditFacilityRepo;

    @PostMapping("/createLoan")
    public ResponseEntity<Map<String, String>> setEmployeeSalary(@RequestBody CreditFacility loan) {
        creditFacilityService.createLoan(loan);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Bank loan");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/updateLoanStatus/{facilityId}")
    public ResponseEntity<Map<String, String>> updateEmployeeSalary(@PathVariable String facilityId){
        CreditFacility loan = creditFacilityRepo.findById(facilityId).orElseThrow(null);
        creditFacilityService.updateLoanStatus(loan);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Current Loan Status Updated");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
