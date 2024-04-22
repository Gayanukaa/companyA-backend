package com.companyA.backend.FinanceSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "employeeSalary")
public class EmployeeSalary {

    @Id
    private String employeeId;
    private String employeeName;
    private double basicSalary;
    private double payForOtHour;
    private double otHours;
    private int numberOfAbsentDays;
    private double grossSalary;
    private double netSalary;
    private double tax;

    public double calGrossSalary(double basicSalary, double otHours, double payForOtHour, int numberOfAbsentDays){
        double calculatedGrossSalary;
        calculatedGrossSalary = basicSalary + otHours*payForOtHour - numberOfAbsentDays*500;
        return calculatedGrossSalary;
    }

    public double calTax(){
        double calculatedTax;
        if (100000< grossSalary && grossSalary <500000){
            calculatedTax = grossSalary*0.05;
        } else if (500000<grossSalary && grossSalary<1000000) {
            calculatedTax = 500000*0.05 + (grossSalary-500000)*0.08;
        }
        else if(grossSalary>1000000){
            calculatedTax = grossSalary*0.1;
        }
        else{
            calculatedTax = 0;
        }
        return calculatedTax;
    }

    public double calNetSalary(){
        double calculatedNetSalary;
        calculatedNetSalary = grossSalary - tax;
        return calculatedNetSalary;
    }
}
