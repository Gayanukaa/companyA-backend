package com.companyA.backend.SalesSystem.service;

import com.companyA.backend.SalesSystem.model.CustomerData;
import com.companyA.backend.SalesSystem.model.CustomerDataFinance;
import com.companyA.backend.SalesSystem.model.FinanceSalesTableBody;
import com.companyA.backend.SalesSystem.model.SalesRecord;
import com.companyA.backend.SalesSystem.repository.FinanceSalesTableRepository;
import com.companyA.backend.SalesSystem.repository.SalesTableRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class FinanceSalesTableService {

    @Autowired
    private FinanceSalesTableRepository financeSalesTableRepository;
    public boolean validateID(String entryID){
        FinanceSalesTableBody existingEntry = financeSalesTableRepository.findById(entryID).orElse(null);
        if (existingEntry != null && existingEntry.get_id()!= null){
            return true;
        }
        return false;
    }

    public FinanceSalesTableBody saveOrder(FinanceSalesTableBody orderDocument) {
        return financeSalesTableRepository.save(orderDocument);  // Save the document
    }

    public FinanceSalesTableBody preProcessing(CustomerDataFinance orderBody){
        List<FinanceSalesTableBody> order = orderBody.getOrders();
        return order.get(0);
    }
    public boolean validateIDFin(String entryID){
        FinanceSalesTableBody existingEntry = financeSalesTableRepository.findById(entryID).orElse(null);
        if (existingEntry != null && existingEntry.get_id()!= null){
            return true;
        }
        return false;
    }

    public String generateID() {
        boolean intIsCompete = false;
        String idStr = null;
        while (!intIsCompete) {
            Random random = new Random();
            long idInt = random.nextLong();
            idStr = String.valueOf(Math.abs(idInt));
            boolean isValid = validateIDFin(idStr);
            System.out.println("isValid");
            System.out.println(isValid);
            if (!isValid) {
                break;
            }
        }
        return idStr;
    }

}
