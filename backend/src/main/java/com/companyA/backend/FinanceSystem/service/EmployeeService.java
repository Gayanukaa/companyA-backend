package com.companyA.backend.FinanceSystem.service;

import com.companyA.backend.FinanceSystem.model.EmployeeSalary;
import com.companyA.backend.FinanceSystem.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


    public EmployeeSalary getEmployee(int employeeId) {
        Optional<EmployeeSalary> salary = employeeRepo.findById(employeeId);

        EmployeeSalary employeeSalary = null;

        if(salary.isPresent()){
            employeeSalary = salary.get();
        }
        return employeeSalary;
    }
    public void updateEmployee(EmployeeSalary employeeSalary){
        double calculatedGross = employeeSalary.calGrossSalary(employeeSalary.getBasicSalary(), employeeSalary.getNormalHours(), employeeSalary.getOtHours(), employeeSalary.getPayForNormalHour(), employeeSalary.getPayForOtHour());
        employeeSalary.setGrossSalary(calculatedGross);
        double calculatedTax = employeeSalary.calTax();
        employeeSalary.setTax(calculatedTax);
        double calculatedNet = employeeSalary.calNetSalary();
        employeeSalary.setNetSalary(calculatedNet);
        employeeRepo.save(employeeSalary);
    }
}
