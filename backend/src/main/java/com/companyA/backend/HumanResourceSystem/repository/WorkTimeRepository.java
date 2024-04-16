package com.companyA.backend.HumanResourceSystem.repository;

import com.companyA.backend.HumanResourceSystem.model.WorkTimeModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface WorkTimeRepository extends MongoRepository<WorkTimeModel, String> {
    List<WorkTimeModel> findByIdAndDate(String id, LocalDate date);
}
