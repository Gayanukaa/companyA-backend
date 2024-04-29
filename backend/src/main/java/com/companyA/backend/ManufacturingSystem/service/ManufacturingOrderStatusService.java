package com.companyA.backend.ManufacturingSystem.service;

import com.companyA.backend.ManufacturingSystem.model.OrderStatus;
import com.companyA.backend.ManufacturingSystem.repository.ManufacturingOrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManufacturingOrderStatusService {
    @Autowired
    private ManufacturingOrderStatusRepository manufacturingOrderStatusRepository;

    // Method to retrieve order status by order ID
    public OrderStatus getOrderStatus(String orderID) {
        return manufacturingOrderStatusRepository.findByOrderID(orderID);
    }

    // Method to update order status by order ID
    public OrderStatus updateOrderStatus(String orderID, String status) {
        // Find the order status by order ID
        OrderStatus orderStatus;
        // Update the status
        orderStatus = manufacturingOrderStatusRepository.findByOrderID(orderID);
        orderStatus.setStatus(status);
        // Save and return the updated order status
        return manufacturingOrderStatusRepository.save(orderStatus);
    }
}