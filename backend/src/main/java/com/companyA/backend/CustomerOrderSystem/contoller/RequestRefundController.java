package com.companyA.backend.CustomerOrderSystem.contoller;
import java.util.HashMap;
import java.util.Map;

import com.companyA.backend.CustomerOrderSystem.model.RequestRefund;
import com.companyA.backend.CustomerOrderSystem.service.RequestRefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RequestRefundController {
    @Autowired
    private RequestRefundService requestRefundService;

    @PostMapping("/requestRefund")
    public ResponseEntity<Map<String, String>> requestRefundById(@RequestBody RequestRefund order_ID) {
        requestRefundService.requestRefund(order_ID);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Refund request sent successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}