package com.companyA.backend.ManufacturingSystem.service;

import com.companyA.backend.ManufacturingSystem.model.OrderStatus;
import com.companyA.backend.ManufacturingSystem.repository.OrderStatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderStatusService {

    private OrderStatusRepository orderStatusRepository;

    public OrderStatus getOrderStatus(String order_ID) {
        return orderStatusRepository.findByOrderID(order_ID);
    }

    public OrderStatus updateOrderStatus(String order_ID, String status) {
        OrderStatus orderStatus;
        orderStatus = orderStatusRepository.findByOrderID(order_ID);
        orderStatus.setStatus(status);
        return orderStatusRepository.save(orderStatus);
    }
}