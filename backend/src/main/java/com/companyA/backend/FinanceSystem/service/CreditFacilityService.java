package com.companyA.backend.FinanceSystem.service;

import com.companyA.backend.FinanceSystem.model.CreditFacility;
import com.companyA.backend.FinanceSystem.repository.CreditFacilityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CreditFacilityService {
    @Autowired
    private CreditFacilityRepo creditFacilityRepo;

    public void createLoan(CreditFacility creditFacility){
        creditFacility.setMonthlyLoanAmount(creditFacility.monthlyLoanPayment());
        creditFacility.setCurrentLoanAmount(creditFacility.getInitialLoanAmount());
        creditFacility.setLoanStatus("Active");
        creditFacility.setStartDate(LocalDate.now());
        creditFacilityRepo.save(creditFacility);
    }
    public void updateLoanStatus(CreditFacility creditFacility){
        creditFacility.recordPayment(creditFacility.getMonthlyLoanAmount());
        creditFacilityRepo.save(creditFacility);
    }



}
