package com.companyA.backend.FinanceSystem.service;


import com.companyA.backend.FinanceSystem.model.EmployeeSalary;
import com.companyA.backend.FinanceSystem.model.UtilityBill;
import com.companyA.backend.FinanceSystem.repository.UtilityBillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UtilityBillService {
    @Autowired
    private UtilityBillRepo utilityBillRepo;

    public List<UtilityBill> getAllBills() {
        return utilityBillRepo.findAll();
    }
    public void payWaterBills(UtilityBill paidBill) {
        paidBill.setWaterBillAmount(paidBill.getBillAmount());
        utilityBillRepo.save(paidBill);
    }

    public void payElectricityBills(UtilityBill paidBill) {
        paidBill.setElectricityBillAmount(paidBill.getBillAmount());
        utilityBillRepo.save(paidBill);
    }

    public void payRentBills(UtilityBill paidBill) {
        paidBill.setRentBillAmount(paidBill.getBillAmount());
        utilityBillRepo.save(paidBill);
    }

}
