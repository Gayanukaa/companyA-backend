package com.companyA.backend.ManufacturingSystem.contoller;

import com.companyA.backend.ManufacturingSystem.model.OrderStatus;
import com.companyA.backend.ManufacturingSystem.service.OrderStatusService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/production/task")
public class OrderStatusController {

    private OrderStatusService orderStatusService;

    // Endpoint to retrieve order status by order ID
    @GetMapping("/updateGetOrderStatus")
    public ResponseEntity<Object> getOrderStatus(@RequestParam("order_ID") String order_ID) {
        // Call the service to get order status
        OrderStatus response = orderStatusService.getOrderStatus(order_ID);
        // Check if order status is null
        if (response == null) {
            // Return not found response if order status is null
            return new ResponseEntity<>("Invalid Order ID", HttpStatus.NOT_FOUND);
        }
        // Return order status if found
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Endpoint to update order status by order ID
    @PostMapping("/updatePostOrderStatus")
    public ResponseEntity<String> updateOrderStatus(@RequestParam("order_ID") String order_ID, @RequestParam("status") String status) {
        // Call the service to update order status
        OrderStatus response = orderStatusService.updateOrderStatus(order_ID, status);
        // Check if order status is null
        if (response == null) {
            // Return not found response if order status is null
            return new ResponseEntity<>("Invalid Order ID", HttpStatus.NOT_FOUND);
        }
        // Return success message if order status is updated successfully
        return new ResponseEntity<>("Order " + response.getOrderID() + " status updated to " + response.getStatus() + " successfully", HttpStatus.OK);
    }
}