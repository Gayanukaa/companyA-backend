package com.companyA.backend.HumanResourceSystem.repository;

import com.companyA.backend.HumanResourceSystem.model.DailyAttendanceModel;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@NonNullApi
public interface PayRollRepository extends MongoRepository<DailyAttendanceModel,String> {
    List<DailyAttendanceModel> findByUidAndDateBetween(String uid, LocalDate startDate, LocalDate endDate);
}
