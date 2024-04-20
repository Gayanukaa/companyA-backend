package com.companyA.backend.HumanResourceSystem.contoller;

import com.companyA.backend.HumanResourceSystem.model.DailyAttendanceModel;
import com.companyA.backend.HumanResourceSystem.repository.DailyAttendanceRepository;
import com.companyA.backend.HumanResourceSystem.service.DailyAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/Attendance")
public class DailyAttendanceController {
    @Autowired
    private DailyAttendanceService dailyAttendanceService;


    //Checks if the given employee is present today.
    @GetMapping("/GetAttendance/{employeeId}")
    public ResponseEntity<DailyAttendanceModel> getAttendance(@PathVariable String employeeId){
        DailyAttendanceModel weeklyAttendance = dailyAttendanceService.getAttendance(employeeId);
        return ResponseEntity.ok(weeklyAttendance);

    }

    //to check given employee's attendance in week
    @GetMapping("/GetWeeklyAttendance/{employeeId}")
    public ResponseEntity<List<DailyAttendanceModel>> getWeeklyAttendance(@PathVariable String employeeId){
        List<DailyAttendanceModel> weeklyAttendance = dailyAttendanceService.getWeeklyAttendance(employeeId);
        return ResponseEntity.ok(weeklyAttendance);

    }
    //Get All employee's attendance for given day
    @GetMapping("/GetDailyAttendance/{date}")
    public ResponseEntity<List<DailyAttendanceModel>> getDailyAttendance(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<DailyAttendanceModel> dailyAttendanceList = dailyAttendanceService.getDailyAttendance(date);
        return ResponseEntity.ok(dailyAttendanceList);
    }

    @PostMapping("/SignIn/{employeeId}")
    public ResponseEntity<String> markSignInAndAttendance(@PathVariable String employeeId){
        dailyAttendanceService.markSignInAndAttendance(employeeId);
        return ResponseEntity.ok("Sign-in time recorded successfully");
    }
    @PostMapping("/SignOut/{employeeId}")
    public ResponseEntity<String> markSignOut(@PathVariable String employeeId){
        dailyAttendanceService.markSignOut(employeeId);
        return ResponseEntity.ok("Sign-out time recorded successfully");

    }


}
