package com.companyA.backend.FinanceSystem.contoller;

import com.companyA.backend.FinanceSystem.repository.StockAlertRepo;
import com.companyA.backend.FinanceSystem.service.StockAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StockAlertController {
    @Autowired
    private StockAlertService stockAlertService;
    @Autowired
    private StockAlertRepo stockAlertRepo;

    @GetMapping("/order")
    public boolean sendRequestForPaymentConfirmation(){
        return stockAlertService.sendRequestForPaymentConfirmation(stockAlertRepo.findAll());
    }
}
