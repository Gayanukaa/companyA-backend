package com.companyA.backend.HumanResourceSystem.repository;

import com.companyA.backend.HumanResourceSystem.model.DailyAttendanceModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DailyAttendanceRepository extends MongoRepository<DailyAttendanceModel,String> {

    List<DailyAttendanceModel> findByDate(LocalDate date);

    DailyAttendanceModel findByEmployeeIdAndDate(String employeeId, LocalDate date);

    List<DailyAttendanceModel> findByEmployeeIdAndDateBetween(String id, LocalDate startDate, LocalDate endDate);
}
