package com.companyA.backend.FinanceSystem.service;


import com.companyA.backend.FinanceSystem.model.FinanceStockAlert;
import com.companyA.backend.FinanceSystem.model.Stocks;
import com.companyA.backend.FinanceSystem.repository.PaymentRepo;
import com.companyA.backend.FinanceSystem.repository.StocksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FinanceStockAlertService {

    @Autowired
    private StocksRepo stocksRepo;

    @Autowired
    private PaymentRepo paymentRepo;

    public boolean sendRequestForPaymentConfirmation(List<FinanceStockAlert> financeStockAlerts){
        double totalCost = 0;
        double unitPrice;
        for (FinanceStockAlert financeStockAlert : financeStockAlerts) {
            Optional<Stocks> stock = stocksRepo.findById(financeStockAlert.getItemId());

            Stocks item = null;

            if(stock.isPresent()){
                item = stock.get();
            }
            unitPrice = item.getPrice();
            totalCost = totalCost + financeStockAlert.getReorderQuantity() * unitPrice;
        }
        if(totalCost> 10000){
            return false;
        }
        else{
            return true;
        }
    }
}
