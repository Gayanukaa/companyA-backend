package com.companyA.backend.HumanResourceSystem.repository;

import com.companyA.backend.HumanResourceSystem.model.DailyAttendanceModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DailyAttendanceRepository extends MongoRepository<DailyAttendanceModel,String> {
    DailyAttendanceModel findByIdAndDate(String id, LocalDate date);


    //List<DailyAttendanceModel> findByIdAndDateBetween(String id, LocalDate startDate, LocalDate endDate);

    List<DailyAttendanceModel> findByDate(LocalDate date);

    //Optional<DailyAttendanceModel> findByEmployeeId(String employeeId);

    DailyAttendanceModel findByEmployeeIdAndDate(String employeeId, LocalDate date);

    List<DailyAttendanceModel> findByEmployeeIdAndDateBetween(String id, LocalDate startDate, LocalDate endDate);
}
