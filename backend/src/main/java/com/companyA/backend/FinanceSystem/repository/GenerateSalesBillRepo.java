package com.companyA.backend.FinanceSystem.repository;

import com.companyA.backend.FinanceSystem.model.GenerateSalesBill;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenerateSalesBillRepo extends MongoRepository<GenerateSalesBill,String> {
}
