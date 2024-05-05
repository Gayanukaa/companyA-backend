package com.companyA.backend.FinanceSystem.service;


import com.companyA.backend.FinanceSystem.model.Payment;
import com.companyA.backend.FinanceSystem.model.UtilityBill;
import com.companyA.backend.FinanceSystem.repository.PaymentRepo;
import com.companyA.backend.FinanceSystem.repository.UtilityBillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UtilityBillService {
    @Autowired
    private UtilityBillRepo utilityBillRepo;

    public void payUtilityBills(UtilityBill bill){
        bill.setBillsSum(bill.calculateTotalBillAmount(bill.getWaterBillAmount(),bill.getElectricityBillAmount(),bill.getRentBillAmount()));
        utilityBillRepo.save(bill);
    }
    public List<UtilityBill> getAllBills(){
        return utilityBillRepo.findAll();
    }







}
