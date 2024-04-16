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


    @GetMapping("/GetWeeklyAttendance/{id}")
    public ResponseEntity<List<DailyAttendanceModel>> getWeeklyAttendance(@PathVariable String id){
        List<DailyAttendanceModel> weeklyAttendace = dailyAttendanceService.getWeeklyAttendace(id);
        return ResponseEntity.ok(weeklyAttendace);

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
