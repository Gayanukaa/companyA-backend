package com.companyA.backend.FinanceSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "UtilityBills")
public class UtilityBill {
    @Id
    private String billId;
    private double billsSum;
    private double waterBillAmount;
    private double electricityBillAmount;
    private double rentBillAmount;

    public double calculateTotalBillAmount(double waterBillAmount,double electricityBillAmount,double rentBillAmount){
        double totalBillAmount;
        totalBillAmount = waterBillAmount+electricityBillAmount+rentBillAmount;
        return totalBillAmount;
    }

}


