package com.companyA.backend.ManufacturingSystem.contoller;

import com.companyA.backend.ManufacturingSystem.model.OrderStatus;
import com.companyA.backend.ManufacturingSystem.service.ManufacturingOrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/production/task")
public class ManufacturingOrderStatusController {
    @Autowired
    private ManufacturingOrderStatusService manufacturingOrderStatusService;

    // Endpoint to retrieve order status by order ID
    @GetMapping("/GetOrderStatus")
    public ResponseEntity<Object> getOrderStatus(@RequestParam("orderID") String orderID) {
        // Call the service to get order status
        OrderStatus response = manufacturingOrderStatusService.getOrderStatus(orderID);
        // Check if order status is null
        if (response == null) {
            // Return not found response if order status is null
            return new ResponseEntity<>("Invalid Order ID", HttpStatus.NOT_FOUND);
        }
        // Return order status if found
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Endpoint to update order status by order ID
    @PostMapping("/updateOrderStatus")
    public ResponseEntity<String> updateOrderStatus(@RequestParam("orderID") String orderID, @RequestParam("status") String status) {
        // Call the service to update order status
        OrderStatus response = manufacturingOrderStatusService.updateOrderStatus(orderID, status);
        // Check if order status is null
        if (response == null) {
            // Return not found response if order status is null
            return new ResponseEntity<>("Invalid Order ID", HttpStatus.NOT_FOUND);
        }
        // Return success message if order status is updated successfully
        return new ResponseEntity<>("Order " + response.getOrderID() + " status updated to " + response.getStatus() + " successfully", HttpStatus.OK);
    }
}