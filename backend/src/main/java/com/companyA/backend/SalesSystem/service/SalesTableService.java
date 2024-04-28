package com.companyA.backend.SalesSystem.service;

import com.companyA.backend.SalesSystem.model.CustomerData;
import com.companyA.backend.SalesSystem.model.Existing;
import com.companyA.backend.SalesSystem.model.FinanceSalesTableBody;
import com.companyA.backend.SalesSystem.model.SalesRecord;
import com.companyA.backend.SalesSystem.repository.FinanceSalesTableRepository;
import com.companyA.backend.SalesSystem.repository.SalesTableRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class SalesTableService {

    @Autowired
    private SalesTableRepository salesTableRepository;


    public List<CustomerData> allRecords() {
        return salesTableRepository.findAll();
    }
    public void updatePurchaseHistory(ObjectId customerId, List<SalesRecord> purchase) {
        CustomerData customer = salesTableRepository.findById(customerId).get();
        if (customer.getOrders() == null) {
            customer.setOrders(new ArrayList<>());
        }
        customer.getOrders().add(purchase.get(0));
        salesTableRepository.save(customer);
    }

    public CustomerData saveOrder(CustomerData orderDocument) {
        return salesTableRepository.save(orderDocument);  // Save the document
    }

    public CustomerData addOrder(ObjectId documentId, SalesRecord newOrder) {
        CustomerData existingDocument = salesTableRepository.findById(documentId).orElse(null);
        if (existingDocument != null) {
            existingDocument.addOrder(newOrder);  // Use addOrder method (optional)
            salesTableRepository.save(existingDocument);  // Save the updated document
            return existingDocument;
        } else {
            // Handle case where document with documentId is not found
            return null;
        }
    }
    public boolean validateID(ObjectId documentId){
        CustomerData existingDocument1 = salesTableRepository.findById(documentId).orElse(null);
        if (existingDocument1 != null && existingDocument1.get_id() != null){
            System.out.println("True");
            return true;
        }
        System.out.println("False");
        return false;
    }




}
