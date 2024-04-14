package com.companyA.backend.ManufacturingSystem.contoller;

import com.companyA.backend.ManufacturingSystem.model.OrderStatus;
import com.companyA.backend.ManufacturingSystem.service.OrderStatusService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OrderStatusController {

    private OrderStatusService orderStatusService;

    @GetMapping("/api/production/task/updateOrderStatus")
    public ResponseEntity<OrderStatus> getOrderStatus(@RequestParam("order_ID") String order_ID) {
        OrderStatus response = orderStatusService.getOrderStatus(order_ID);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/api/production/task/updateOrderStatus")
    public ResponseEntity<String> updateOrderStatus(@RequestParam("order_ID") String order_ID, @RequestParam("status") String status) {
        OrderStatus response = orderStatusService.updateOrderStatus(order_ID, status);
        return new ResponseEntity<>("Order " + response.getOrder_ID() + " status updated to " + response.getStatus() + " successfully", HttpStatus.OK);
    }
}