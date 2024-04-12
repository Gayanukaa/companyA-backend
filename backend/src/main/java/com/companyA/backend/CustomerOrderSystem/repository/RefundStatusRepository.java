package com.companyA.backend.CustomerOrderSystem.repository;
import java.util.Optional;

import com.companyA.backend.CustomerOrderSystem.model.RefundStatus;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefundStatusRepository extends MongoRepository<RefundStatus, ObjectId> {
    Optional<RefundStatus> findById(String orderId);
}
