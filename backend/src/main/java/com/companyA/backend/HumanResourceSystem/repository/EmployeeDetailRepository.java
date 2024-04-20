package com.companyA.backend.HumanResourceSystem.repository;

import com.companyA.backend.HumanResourceSystem.model.EmployeeDetailModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDetailRepository extends MongoRepository<EmployeeDetailModel,String> {
    EmployeeDetailModel findByemployeeIdAndIsActive(String employeeId, boolean active);
    List<EmployeeDetailModel> findByLastNameAndIsActive(String lastName, boolean active);

}
