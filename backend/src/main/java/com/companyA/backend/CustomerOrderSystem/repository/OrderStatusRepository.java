package com.companyA.backend.CustomerOrderSystem.repository;
import java.util.Optional;

import com.companyA.backend.CustomerOrderSystem.model.OrderStatus;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends MongoRepository<OrderStatus, ObjectId> {
    Optional<OrderStatus> findById(String orderId);
}
