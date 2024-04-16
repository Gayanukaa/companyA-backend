package com.companyA.backend.HumanResourceSystem.repository;

import com.companyA.backend.HumanResourceSystem.model.DailyAttendanceModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DailyAttendanceRepository extends MongoRepository<DailyAttendanceModel,String> {

}
