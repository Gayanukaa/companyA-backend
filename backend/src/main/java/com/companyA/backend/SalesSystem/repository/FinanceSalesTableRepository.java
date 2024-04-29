package com.companyA.backend.SalesSystem.repository;

import com.companyA.backend.SalesSystem.model.CustomerData;
import com.companyA.backend.SalesSystem.model.FinanceSalesTableBody;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface FinanceSalesTableRepository extends MongoRepository<FinanceSalesTableBody, String> {
    Optional<FinanceSalesTableBody> findById(String id);

    FinanceSalesTableBody save(FinanceSalesTableBody data);
}
