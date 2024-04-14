package com.companyA.backend.FinanceSystem.repository;

import com.companyA.backend.FinanceSystem.model.EmployeeSalary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends MongoRepository<EmployeeSalary,Integer> {
}
