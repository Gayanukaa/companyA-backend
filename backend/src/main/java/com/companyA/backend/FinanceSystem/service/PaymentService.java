package com.companyA.backend.FinanceSystem.service;

import com.companyA.backend.FinanceSystem.model.EmployeeSalary;
import com.companyA.backend.FinanceSystem.model.Payment;
import com.companyA.backend.FinanceSystem.repository.EmployeeSalaryRepo;
import com.companyA.backend.FinanceSystem.repository.PaymentRepo;
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


    public void SalaryPaymentConfirmation(Payment payment){
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





}



