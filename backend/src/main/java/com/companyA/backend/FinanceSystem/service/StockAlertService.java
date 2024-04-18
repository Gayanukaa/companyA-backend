package com.companyA.backend.FinanceSystem.service;

import com.companyA.backend.FinanceSystem.model.StockAlert;
import com.companyA.backend.FinanceSystem.model.Stocks;
import com.companyA.backend.FinanceSystem.repository.StocksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockAlertService {

    @Autowired
    private StocksRepo stocksRepo;

    public boolean sendRequestForPaymentConfirmation(List<StockAlert> stockAlerts){
        double totalCost = 0;
        double unitPrice;
        for (StockAlert stockAlert : stockAlerts) {
            Optional<Stocks> stock = stocksRepo.findById(stockAlert.getItemId());

            Stocks item = null;

            if(stock.isPresent()){
                item = stock.get();
            }
            unitPrice = item.getPrice();
            totalCost = totalCost + stockAlert.getReorderQuantity() * unitPrice;
        }
        if(totalCost> 10000){
            System.out.println(totalCost);
            return false;
        }
        else{
            System.out.println(totalCost);
            return true;
        }
    }

}
