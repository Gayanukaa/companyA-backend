package com.companyA.backend.FinanceSystem.contoller;

import com.companyA.backend.FinanceSystem.model.EmployeeSalary;
import com.companyA.backend.FinanceSystem.model.UtilityBill;
import com.companyA.backend.FinanceSystem.service.IDNotFoundException;
import com.companyA.backend.FinanceSystem.service.PaymentService;
import com.companyA.backend.FinanceSystem.service.UtilityBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UtilityBillController {
    @Autowired
    private UtilityBillService utilityBillService;


    @GetMapping("/getBills")
    public List<UtilityBill> getAllBills() {
        return utilityBillService.getAllBills();
    }

    @PostMapping("/StoreUtilityBill")
    public ResponseEntity<Map<String, String>> utilityBill(@RequestBody UtilityBill paidBill) {
        utilityBillService.payUtilityBills(paidBill);
        Map<String, String> response = new HashMap<>();
        response.put("message", "UtilityBill is Successfully Added");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
