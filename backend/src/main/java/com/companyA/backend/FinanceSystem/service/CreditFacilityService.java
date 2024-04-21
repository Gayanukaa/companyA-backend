package com.companyA.backend.FinanceSystem.service;

import com.companyA.backend.FinanceSystem.model.CreditFacility;
import com.companyA.backend.FinanceSystem.repository.CreditFacilityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CreditFacilityService {
    @Autowired
    private CreditFacilityRepo creditFacilityRepo;
    @Scheduled(cron = "0 0 0 1 **")
    public boolean monthlyLoanPaymnet(CreditFacility creditFacility) {
        creditFacility.setMonthlyLoanAmount(creditFacility.monthlyLoanPayment());
        creditFacility.recordPayment(creditFacility.getMonthlyLoanAmount());
        creditFacilityRepo.save(creditFacility);
        return true;
    }
}
