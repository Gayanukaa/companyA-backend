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
    @GetMapping("/GetAttendance/{id}")
    public ResponseEntity<DailyAttendanceModel> getAttendance(@PathVariable String id){
        DailyAttendanceModel weeklyAttendance = dailyAttendanceService.getAttendance(id);
        return ResponseEntity.ok(weeklyAttendance);

    }

    //to check given employee's attendance in week
    @GetMapping("/GetWeeklyAttendance/{id}")
    public ResponseEntity<List<DailyAttendanceModel>> getWeeklyAttendance(@PathVariable String id){
        List<DailyAttendanceModel> weeklyAttendance = dailyAttendanceService.getWeeklyAttendance(id);
        return ResponseEntity.ok(weeklyAttendance);

    }

    @GetMapping("/GetDailyAttendance/{date}")
    public ResponseEntity<List<DailyAttendanceModel>> getDailyAttendance(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<DailyAttendanceModel> dailyAttendanceList = dailyAttendanceService.getDailyAttendance(date);
        return ResponseEntity.ok(dailyAttendanceList);
    }

    @PostMapping("/SignIn/{id}")
    public ResponseEntity<String> markSigIn(@PathVariable String id){
        dailyAttendanceService.markSignInAndAttendance(id);
        return ResponseEntity.ok("Sign-in time recorded successfully");
    }
    @PostMapping("/SignOut/{id}")
    public ResponseEntity<String> markSignOut(@PathVariable String id){
        dailyAttendanceService.markSignOut(id);
        return ResponseEntity.ok("Sign-out time recorded successfully");

    }


}
