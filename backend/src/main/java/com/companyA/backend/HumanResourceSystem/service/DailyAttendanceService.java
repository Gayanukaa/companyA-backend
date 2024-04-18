package com.companyA.backend.HumanResourceSystem.service;

import com.companyA.backend.HumanResourceSystem.model.DailyAttendanceModel;
import com.companyA.backend.HumanResourceSystem.repository.DailyAttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DailyAttendanceService {

    @Autowired
    DailyAttendanceRepository dailyAttendanceRepository;

    public void markSignInAndAttendance(String id){
        DailyAttendanceModel dailyAttendance = new DailyAttendanceModel();
        dailyAttendance.setId(id);
        dailyAttendance.setPresent(true);
        dailyAttendance.setDate(LocalDate.now());
        dailyAttendance.setSignInTime(LocalDateTime.now());
        dailyAttendanceRepository.save(dailyAttendance);
    }
    public void markSignOut(String id){
        DailyAttendanceModel dailyAttendance = dailyAttendanceRepository.findByIdAndDate(id,LocalDate.now());
        if (dailyAttendance.isPresent()){
            DailyAttendanceModel attendance = dailyAttendance;
            attendance.setSignOutTime(LocalDateTime.now());
            dailyAttendanceRepository.save(attendance);

        }
        else{
            throw new RuntimeException("Attendance not found for employee with id: " + id);
        }
    }

    public DailyAttendanceModel getAttendance(String id){

        LocalDate date = LocalDate.now();
        return dailyAttendanceRepository.findByIdAndDate(id,date);

    }

    public List<DailyAttendanceModel> getWeeklyAttendance(String id){

        LocalDate startDate = LocalDate.now().minusDays(7);
        LocalDate endDate = LocalDate.now();

        return dailyAttendanceRepository.findByIdAndDateBetween(id, startDate, endDate);

    }

    public List<DailyAttendanceModel> getDailyAttendance(LocalDate date){

        return dailyAttendanceRepository.findByDate(date);

    }
}


