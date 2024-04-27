package com.companyA.backend.FinanceSystem.repository;

import com.companyA.backend.FinanceSystem.model.SalesRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRecordRepo extends MongoRepository<SalesRecord,String> {
}
