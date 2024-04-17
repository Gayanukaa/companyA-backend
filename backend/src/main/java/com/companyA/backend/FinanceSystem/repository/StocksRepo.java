package com.companyA.backend.FinanceSystem.repository;

import com.companyA.backend.FinanceSystem.model.Stocks;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StocksRepo extends MongoRepository<Stocks,String> {

}
