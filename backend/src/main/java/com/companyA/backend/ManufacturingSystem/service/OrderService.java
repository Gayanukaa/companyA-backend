package com.companyA.backend.ManufacturingSystem.service;

import com.companyA.backend.ManufacturingSystem.model.Employee;
import com.companyA.backend.ManufacturingSystem.model.Order;
import com.companyA.backend.ManufacturingSystem.repository.EmployeeRepository;
import com.companyA.backend.ManufacturingSystem.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// Service class to interact with Order entities
@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;
    private EmployeeRepository employeeRepository;

    // Method to assign a production line to an order
    public void assignProductionLineToOrder(String orderId, String lineId) {
        // Check if order exists
        Order order = orderRepository.findByOrderId(orderId);
        if (order == null) {
            // Handle error: Order not found
            return;
        }

        // Assign the production line to the order
        order.setLineId(lineId);
        orderRepository.save(order);
    }

    // Method to assign workers to a production line for an order
    public void assignWorkersToProductionLine(String orderId, List<String> workerIds) {
        // Check if order exists
        Order order = orderRepository.findByOrderId(orderId);
        if (order == null) {
            // Handle error: Order not found
            return;
        }

        // Retrieve workers from their IDs
        List<Employee> workers = employeeRepository.findAllById(workerIds);

        // Assign workers to the production line for the order
        order.setWorkers(workers);
        orderRepository.save(order);
    }
}

