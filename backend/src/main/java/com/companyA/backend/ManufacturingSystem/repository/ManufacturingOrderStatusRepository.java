package com.companyA.backend.ManufacturingSystem.repository;

import com.companyA.backend.ManufacturingSystem.model.OrderStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturingOrderStatusRepository extends MongoRepository<OrderStatus, String> {
    // Method to find order status by order ID
    OrderStatus findByOrderID(String orderID);
}