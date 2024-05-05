package com.companyA.backend.FinanceSystem.service;

import com.companyA.backend.FinanceSystem.model.EmployeeSalary;
import com.companyA.backend.FinanceSystem.model.GenerateSalesBill;
import com.companyA.backend.FinanceSystem.model.Payment;
import com.companyA.backend.FinanceSystem.model.UtilityBill;
import com.companyA.backend.FinanceSystem.repository.EmployeeSalaryRepo;
import com.companyA.backend.FinanceSystem.repository.GenerateSalesBillRepo;
import com.companyA.backend.FinanceSystem.repository.PaymentRepo;
import com.companyA.backend.FinanceSystem.repository.UtilityBillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private EmployeeSalaryRepo employeeSalaryRepo;

    @Autowired
    private UtilityBillRepo utilityBillRepo;

    @Autowired
    private GenerateSalesBillRepo generateSalesBillRepo;


    public void salaryPaymentConfirmation(Payment payment){
        List<EmployeeSalary> employeeSalaries = employeeSalaryRepo.findAll();
        double totalPayment = 0;
        for(EmployeeSalary employeeSalary : employeeSalaries){
            double salary = employeeSalary.getNetSalary();
            totalPayment = totalPayment + salary;
        }

        payment.setType("Outgoing");
        payment.setStatus("Confirmed");

        payment.setPaymentDate(LocalDateTime.now());
        payment.setAmount(totalPayment);
        paymentRepo.save(payment);

    }

    public void payUtilityBill(Payment payment){
        List<UtilityBill> bills = utilityBillRepo.findAll();
        double totalBillPayment = 0;
        for(UtilityBill bill : bills){
            double billAmount = bill.getBillsSum();
            totalBillPayment = totalBillPayment + billAmount;
        }

        payment.setType("Outgoing");
        payment.setStatus("Confirmed");
        payment.setPaymentDate(LocalDateTime.now());
        payment.setAmount(totalBillPayment);
        paymentRepo.save(payment);

    }
    public void salesIncome(Payment payment){
        List<GenerateSalesBill> sales = generateSalesBillRepo.findAll();
        double totalIncome = 0;
        for(GenerateSalesBill sale : sales){
            totalIncome = totalIncome + sale.getOrder_amount();
        }
        payment.setType("Incoming");
        payment.setStatus("Confirmed");
        payment.setPaymentDate(LocalDateTime.now());
        payment.setAmount(totalIncome);
        paymentRepo.save(payment);

    }










}



