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

    @PostMapping("/payedWaterBills")
    public ResponseEntity<Map<String, String>> setPaidWaterBill(@RequestBody UtilityBill paidBill) {
        utilityBillService.payWaterBills(paidBill);
        Map<String, String> response = new HashMap<>();
        response.put("message", "payedWaterBill is Successfully Added");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/payedElectricityBills")
    public ResponseEntity<Map<String, String>> setPaidElectricityBill(@RequestBody UtilityBill paidBill) {
        utilityBillService.payElectricityBills(paidBill);

        Map<String, String> response = new HashMap<>();
        response.put("message", "payedElectricityBill is Successfully Added");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/payedFactoryRentBills")
    public ResponseEntity<Map<String, String>> setPaidRentBill(@RequestBody UtilityBill paidBill) {
        utilityBillService.payRentBills(paidBill);

        Map<String, String> response = new HashMap<>();
        response.put("message", "payedFactoryRentBills is Successfully Added");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
