package com.companyA.backend.FinanceSystem.service;

import com.companyA.backend.FinanceSystem.model.EmployeeSalary;
import com.companyA.backend.FinanceSystem.repository.EmployeeSalaryRepo;
import com.companyA.backend.HumanResourceSystem.repository.EmployeeDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FinanceEmployeeService {
    @Autowired
    private EmployeeSalaryRepo employeeSalaryRepo;

    @Autowired
    private EmployeeDetailRepository employeeDetailRepository;

    public List<EmployeeSalary> getAllEmployeeSalary(){
        return employeeSalaryRepo.findAll();
    }

    public void addEmployeeSalary(EmployeeSalary employeeSalary){
        double calculatedGross = employeeSalary.calGrossSalary(employeeSalary.getBasicSalary(), employeeSalary.getOtHours(), employeeSalary.getPayForOtHour(), employeeSalary.getNumberOfAbsentDays());
        employeeSalary.setGrossSalary(calculatedGross);
        double calculatedTax = employeeSalary.calTax();
        employeeSalary.setTax(calculatedTax);
        if(employeeDetailRepository.findByEmployeeId(employeeSalary.getEmployeeId()) != null){
            employeeSalary.setEmployeeName(employeeDetailRepository.findByEmployeeId(employeeSalary.getEmployeeId()).getFirstName());
        }
        else {
            // Employee not found, throw an exception
            throw new IDNotFoundException("Employee not found with ID: " + employeeSalary.getEmployeeId());
        }


        double calculatedNet = employeeSalary.calNetSalary();
        employeeSalary.setNetSalary(calculatedNet);
        employeeSalaryRepo.save(employeeSalary);
    }


    public EmployeeSalary getEmployeeSalary(String employeeId) {
        Optional<EmployeeSalary> salary = employeeSalaryRepo.findById(employeeId);

        EmployeeSalary employeeSalary = null;

        if(salary.isPresent()){
            employeeSalary = salary.get();
        }
        else {
            // Employee not found, throw an exception
            throw new IDNotFoundException("Employee not found with ID: " + employeeId);
        }
        return employeeSalary;
    }
    public void updateEmployeeSalary(EmployeeSalary employeeSalary){
        double calculatedGross = employeeSalary.calGrossSalary(employeeSalary.getBasicSalary(), employeeSalary.getOtHours(), employeeSalary.getPayForOtHour(),employeeSalary.getNumberOfAbsentDays());
        employeeSalary.setGrossSalary(calculatedGross);
        double calculatedTax = employeeSalary.calTax();
        employeeSalary.setTax(calculatedTax);
        double calculatedNet = employeeSalary.calNetSalary();
        employeeSalary.setNetSalary(calculatedNet);
        employeeSalaryRepo.save(employeeSalary);
    }
}
