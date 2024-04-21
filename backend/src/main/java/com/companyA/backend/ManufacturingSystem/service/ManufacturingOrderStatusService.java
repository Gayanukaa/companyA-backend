package com.companyA.backend.ManufacturingSystem.service;

import com.companyA.backend.ManufacturingSystem.model.OrderStatus;
import com.companyA.backend.ManufacturingSystem.repository.ManufacturingOrderStatusRepository;
import org.springframework.stereotype.Service;

@Service
public class ManufacturingOrderStatusService {

    private ManufacturingOrderStatusRepository manufacturingOrderStatusRepository;

    // Method to retrieve order status by order ID
    public OrderStatus getOrderStatus(String order_ID) {
        return manufacturingOrderStatusRepository.findByOrderID(order_ID);
    }

    // Method to update order status by order ID
    public OrderStatus updateOrderStatus(String order_ID, String status) {
        // Find the order status by order ID
        OrderStatus orderStatus;
        // Update the status
        orderStatus = manufacturingOrderStatusRepository.findByOrderID(order_ID);
        orderStatus.setStatus(status);
        // Save and return the updated order status
        return manufacturingOrderStatusRepository.save(orderStatus);
    }
}