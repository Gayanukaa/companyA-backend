package com.companyA.backend.ManufacturingSystem.repository;

import com.companyA.backend.ManufacturingSystem.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// Repository interface for CRUD operations on Order entities
@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    // Custom method to find an order by its orderId
    Order findByOrderId(String orderId);
}
