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
    private String waterBill_AccountNo;
    private String electricityBill_AccountNo;
    private double billsSum;
    private double billAmount;
    private double waterBillAmount;
    private double ElectricityBillAmount;
    private double RentBillAmount;

}
