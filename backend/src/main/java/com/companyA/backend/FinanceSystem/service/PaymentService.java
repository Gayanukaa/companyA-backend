package com.companyA.backend.FinanceSystem.service;

import com.companyA.backend.FinanceSystem.model.EmployeeSalary;
import com.companyA.backend.FinanceSystem.model.Payment;
import com.companyA.backend.FinanceSystem.repository.EmployeeRepo;
import com.companyA.backend.FinanceSystem.repository.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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



