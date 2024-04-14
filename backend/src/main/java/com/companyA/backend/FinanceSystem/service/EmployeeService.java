package com.companyA.backend.FinanceSystem.service;

import com.companyA.backend.FinanceSystem.model.EmployeeSalary;
import com.companyA.backend.FinanceSystem.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;

    public List<EmployeeSalary> getAllEmployee(){
        return employeeRepo.findAll();
    }

    public void addEmployee(EmployeeSalary employee){
        double calculatedGross = employee.calGrossSalary(employee.getBasicSalary(), employee.getNormalHours(), employee.getOtHours(), employee.getPayForNormalHour(), employee.getPayForOtHour());
        employee.setGrossSalary(calculatedGross);
        double calculatedTax = employee.calTax();
        employee.setTax(calculatedTax);
        double calculatedNet = employee.calNetSalary();
        employee.setNetSalary(calculatedNet);
        employeeRepo.save(employee);
    }


}
