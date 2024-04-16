package com.companyA.backend.HumanResourceSystem.service;

import com.companyA.backend.HumanResourceSystem.model.AttendanceIdModel;
import com.companyA.backend.HumanResourceSystem.model.DailyAttendanceModel;
import com.companyA.backend.HumanResourceSystem.repository.AttendanceIdRepository;
import com.companyA.backend.HumanResourceSystem.repository.DailyAttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DailyAttendanceService {

    @Autowired
    DailyAttendanceRepository dailyAttendanceRepository;


    /*
    public void markSignIn(String employeeId){
        //find employee by ID
        Optional<AttendanceIdModel> employeeOptional = attendanceIdRepository.findByEmployeeId(employeeId);
        if (employeeOptional.isPresent()){
            //create new attendance record for sign in
            AttendanceIdModel employee = employeeOptional.get();
            DailyAttendanceModel attendance = new DailyAttendanceModel();
            attendance.setDate(LocalDate.now()); // Set the date
            attendance.setEmployeeId(employee);
            attendance.setSignInTime(LocalDateTime.now());
            //save attendance record
            dailyAttendanceRepository.save(attendance);
        }
        else {
            throw new RuntimeException("Employee not found with ID: " + employeeId);
        }
    }

    public void markSignOut(String employeeId){
        Optional<DailyAttendanceModel> attendanceOptional = dailyAttendanceRepository.findByEmployeeIdAndDate(employeeId,LocalDate.now());
        if (attendanceOptional.isPresent()){
            DailyAttendanceModel attendance = attendanceOptional.get();
            attendance.setSignOutTime(LocalDateTime.now());

            // Save the updated attendance record
            dailyAttendanceRepository.save(attendance);
        }
    }




    public void markSignInAndAttendance(String employeeId){
        DailyAttendanceModel dailyAttendance = new DailyAttendanceModel();
        dailyAttendance.setId(employeeId);
        dailyAttendance.setPresent(true);
        dailyAttendance.setDate(LocalDate.now());
        dailyAttendance.setSignInTime(LocalDateTime.now());
        dailyAttendanceRepository.save(dailyAttendance);
    }
    public void markSignOut(String employeeId){
        DailyAttendanceModel dailyAttendance = dailyAttendanceRepository.findByEmployeeIdAndDate(employeeId,LocalDate.now());
        if (dailyAttendance != null){
            DailyAttendanceModel attendance = dailyAttendance;
            attendance.setSignOutTime(LocalDateTime.now());
            dailyAttendanceRepository.save(attendance);

        }
        else{
            throw new RuntimeException("Attendance not found for employee with id: " + employeeId);
        }
    }

     */
    public void markSignInAndAttendance(String id){
        DailyAttendanceModel dailyAttendance = new DailyAttendanceModel();
        dailyAttendance.setEmployeeId(id);
        dailyAttendance.setPresent(true);
        dailyAttendance.setDate(LocalDate.now());
        dailyAttendance.setSignInTime(LocalDateTime.now());
        dailyAttendanceRepository.save(dailyAttendance);
    }
    public void markSignOut(String id){
        DailyAttendanceModel dailyAttendance = dailyAttendanceRepository.findByEmployeeIdAndDate(id,LocalDate.now());
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
        return dailyAttendanceRepository.findByEmployeeIdAndDate(id,date);

    }

    public List<DailyAttendanceModel> getWeeklyAttendance(String id){

        LocalDate startDate = LocalDate.now().minusDays(7);
        LocalDate endDate = LocalDate.now();

        return dailyAttendanceRepository.findByEmployeeIdAndDateBetween(id, startDate, endDate);

    }

    public List<DailyAttendanceModel> getDailyAttendance(LocalDate date){

        return dailyAttendanceRepository.findByDate(date);

    }
}


