package com.companyA.backend.FinanceSystem.service;

import com.companyA.backend.FinanceSystem.model.GenerateSalesBill;
import com.companyA.backend.FinanceSystem.repository.GenerateSalesBillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenerateSalesBillService {
    @Autowired
    private GenerateSalesBillRepo salesRepo;

    public String generateBill(GenerateSalesBill generateSalesBill){
        StringBuilder billBuilder = new StringBuilder();
        billBuilder.append("Electric Equipment Warehouse**\n");
        billBuilder.append("**Address: Bandaranayaka road, katubedda\n");
        billBuilder.append("Phone: 0712345643\n\n");

        // Bill Details
        billBuilder.append("**Order Details**\n");
        billBuilder.append("Component Name | Quantity | Unit Price | Amount\n");
        billBuilder.append("---------------------------------------------\n");

        double totalAmount = generateSalesBill.getOrder_amount();

        billBuilder.append(String.format("Total Amount: %.2f\n", totalAmount));
        billBuilder.append("**Thank you for your business!**\n");

        return billBuilder.toString();



    }

    public void addRecord(GenerateSalesBill generateSalesBill){
        salesRepo.save(generateSalesBill);
    }
}
