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

    @Autowired
    private PaymentRepo paymentRepo;

    public Payment paymentWaterBill;

    public Payment paymentElectricityBill;
    public Payment paymentRentBill;

    public UtilityBillService() {
        this.paymentWaterBill = new Payment(); // Initialize payment object
        this.paymentElectricityBill = new Payment();
        this.paymentRentBill = new Payment();
    }

    public List<UtilityBill> getAllBills() {
        return utilityBillRepo.findAll();
    }
    public void payWaterBills(UtilityBill paidBill) {
        paidBill.setWaterBillAmount(paidBill.getBillAmount());
        paymentWaterBill.setAmount(paidBill.getBillAmount());
        paymentWaterBill.setType("Outgoing");
        paymentWaterBill.setStatus("Confirmed");
        paymentWaterBill.setPaymentDate(LocalDateTime.now());
        paymentRepo.save(paymentWaterBill);
        utilityBillRepo.save(paidBill);
    }

    public void payElectricityBills(UtilityBill paidBill) {
        paidBill.setElectricityBillAmount(paidBill.getBillAmount());
        paymentElectricityBill.setAmount(paidBill.getBillAmount());
        paymentElectricityBill.setType("Outgoing");
        paymentElectricityBill.setStatus("Confirmed");
        paymentElectricityBill.setPaymentDate(LocalDateTime.now());
        paymentRepo.save(paymentElectricityBill);
        utilityBillRepo.save(paidBill);
    }

    public void payRentBills(UtilityBill paidBill) {
        paidBill.setRentBillAmount(paidBill.getBillAmount());
        paymentRentBill.setAmount(paidBill.getBillAmount());
        paymentRentBill.setType("Outgoing");
        paymentRentBill.setStatus("Confirmed");
        paymentRentBill.setPaymentDate(LocalDateTime.now());
        paymentRepo.save(paymentRentBill);
        utilityBillRepo.save(paidBill);
    }

}
