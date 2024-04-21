package com.companyA.backend.CustomerOrderSystem.contoller;
import java.util.Optional;

import com.companyA.backend.CustomerOrderSystem.model.RefundStatus;
import com.companyA.backend.CustomerOrderSystem.service.RefundStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
public class RefundStatusController {
    @Autowired
    private RefundStatusService refundStatusService;

    @GetMapping("/getRefundStatus")
    public ResponseEntity<Optional<RefundStatus>> getRefundStatus(@RequestParam("order_ID") String order_ID) {

        Optional<RefundStatus> response = refundStatusService.getRefundStatus(order_ID);
        return new ResponseEntity<Optional<RefundStatus>>(response, HttpStatus.OK);
    }
}
