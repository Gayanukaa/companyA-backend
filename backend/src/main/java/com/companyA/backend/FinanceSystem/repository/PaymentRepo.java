package com.companyA.backend.FinanceSystem.repository;

import com.companyA.backend.FinanceSystem.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends MongoRepository<Payment,Integer> {
}
