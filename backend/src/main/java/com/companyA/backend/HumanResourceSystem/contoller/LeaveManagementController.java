package com.companyA.backend.HumanResourceSystem.contoller;

import com.companyA.backend.HumanResourceSystem.model.DailyAttendanceModel;
import com.companyA.backend.HumanResourceSystem.repository.DailyAttendanceRepository;
import com.companyA.backend.HumanResourceSystem.service.LeaveManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping (value = "/Leave")
public class LeaveManagementController {

    @Autowired
     DailyAttendanceRepository dailyAttendanceRepository;

    @Autowired
    LeaveManagementService leaveManagementService;

    @GetMapping ("/Balance")
    @ResponseBody
    public int getEmployeeLeaveBalance (@RequestParam String employeeId,
                                                   @RequestParam int year,
                                                   @RequestParam int month) {
        List<DailyAttendanceModel> workTimeModels = dailyAttendanceRepository.findByEmployeeId(employeeId);

        LeaveManagementService leaveBalance = new LeaveManagementService() ;
        int satAndSun = leaveBalance.SatAndSun(year, month);
        int days = leaveBalance.DaysOfMonth(year, month);
        List<DailyAttendanceModel> employeeWorkDays = leaveBalance.getWorkTimeByMonthAndYear(workTimeModels, year, month);
        int ans = leaveBalance.CalculateLeaveBalance(employeeWorkDays, days,  satAndSun);
        return ans ;
    }

}
