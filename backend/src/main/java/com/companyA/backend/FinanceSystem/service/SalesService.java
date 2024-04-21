package com.companyA.backend.FinanceSystem.service;

import com.companyA.backend.FinanceSystem.model.SalesRecord;
import com.companyA.backend.FinanceSystem.repository.SalesRecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesService {
    @Autowired
    private SalesRecordRepo salesRepo;

    public String generateBill(SalesRecord salesRecord){
        StringBuilder billBuilder = new StringBuilder();
        billBuilder.append("Electric Equipment Warehouse**\n");
        billBuilder.append("**Address: Bandaranayaka road, katubedda\n");
        billBuilder.append("Phone: 0712345643\n\n");

        // Bill Details
        billBuilder.append("**Order Details**\n");
        billBuilder.append("Component Name | Quantity | Unit Price | Amount\n");
        billBuilder.append("---------------------------------------------\n");

        double totalAmount = salesRecord.getOrder_amount();

        billBuilder.append(String.format("Total Amount: %.2f\n", totalAmount));
        billBuilder.append("**Thank you for your business!**\n");

        return billBuilder.toString();



    }

    public void addRecord(SalesRecord salesRecord){
        salesRepo.save(salesRecord);
    }
}
