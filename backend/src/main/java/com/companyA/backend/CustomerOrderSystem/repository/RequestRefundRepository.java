package com.companyA.backend.CustomerOrderSystem.repository;
import com.companyA.backend.CustomerOrderSystem.model.RequestRefund;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRefundRepository extends MongoRepository<RequestRefund, ObjectId> {
}
