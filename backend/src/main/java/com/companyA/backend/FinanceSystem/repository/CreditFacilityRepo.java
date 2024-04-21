package com.companyA.backend.FinanceSystem.repository;


import com.companyA.backend.FinanceSystem.model.CreditFacility;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CreditFacilityRepo extends MongoRepository<CreditFacility,String> {
}
