package com.companyA.backend.FinanceSystem.service;

import com.companyA.backend.FinanceSystem.model.EmployeeSalary;
import com.companyA.backend.FinanceSystem.model.Payment;
import com.companyA.backend.FinanceSystem.model.StockAlert;
import com.companyA.backend.FinanceSystem.model.Stocks;
import com.companyA.backend.FinanceSystem.repository.EmployeeRepo;
import com.companyA.backend.FinanceSystem.repository.PaymentRepo;
import com.companyA.backend.FinanceSystem.repository.StocksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private EmployeeRepo employeeRepo;


    public void SalaryPaymentConfirmation(int employeeId){
        Optional<EmployeeSalary> employee = employeeRepo.findById(employeeId);

        EmployeeSalary employeeSalary = null;

        if(employee.isPresent()){
            employeeSalary = employee.get();
        }
        double paymentAmount = employeeSalary.getNetSalary();
        Payment payment = new Payment();
        payment.setType("Outgoing");
        payment.setStatus("Salary successfully deposited ");
        payment.setAmount(paymentAmount);
        paymentRepo.save(payment);

    }



}



