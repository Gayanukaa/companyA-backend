package com.companyA.backend.ManufacturingSystem.repository;

import com.companyA.backend.ManufacturingSystem.model.OrderStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends MongoRepository<OrderStatus, String> {
    OrderStatus findByOrderID(String order_ID);
}