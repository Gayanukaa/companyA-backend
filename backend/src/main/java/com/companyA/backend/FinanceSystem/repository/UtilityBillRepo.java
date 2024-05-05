package com.companyA.backend.FinanceSystem.repository;

import com.companyA.backend.FinanceSystem.model.EmployeeSalary;
import com.companyA.backend.FinanceSystem.model.UtilityBill;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilityBillRepo extends MongoRepository<UtilityBill,String> {
}
