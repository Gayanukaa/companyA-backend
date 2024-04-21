package com.companyA.backend.FinanceSystem.contoller;

import com.companyA.backend.FinanceSystem.model.CreditFacility;
import com.companyA.backend.FinanceSystem.service.CreditFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CreditFacilityController {

    @Autowired
    private CreditFacilityService creditFacilityService;

    public boolean loanPaymentConfirmation(@RequestBody CreditFacility creditFacility){
        return creditFacilityService.monthlyLoanPaymnet(creditFacility);
    }
}
