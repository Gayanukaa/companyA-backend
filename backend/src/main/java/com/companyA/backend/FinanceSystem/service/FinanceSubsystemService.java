package com.companyA.backend.FinanceSystem.service;

import com.companyA.backend.InventoryManagementSystem.model.StockAlert;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
@Service
public class FinanceSubsystemService {

    public boolean sendRequestForPaymentConfirmation(List<StockAlert> stockAlerts) {
        // API request to finance subsystem
        // Sending list of stocks to be ordered
        // Get each stock.reorderQuantity and stock.price and do your payments
        // Receive response for payment confirmation
        // Assuming the payment is successful
        return true;
    }

}
