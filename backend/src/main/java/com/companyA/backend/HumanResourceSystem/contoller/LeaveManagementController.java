package com.companyA.backend.HumanResourceSystem.contoller;

import com.companyA.backend.HumanResourceSystem.model.WorkTimeModel;
import com.companyA.backend.HumanResourceSystem.repository.LeaveManagementRepository;
import com.companyA.backend.HumanResourceSystem.service.LeaveManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping (value = "/Leave")
public class LeaveManagementController {

    @Autowired
    LeaveManagementRepository leaveManagementRepository ;
    LeaveManagementService leaveManagementService;

    @GetMapping ("/Balance")
    @ResponseBody
    public int getEmployeeLeaveBalance (@RequestParam String employeeId,
                                                   @RequestParam int year,
                                                   @RequestParam int month) {
        List<WorkTimeModel> workTimeModels = leaveManagementRepository.findByEmployeeId(employeeId);

        LeaveManagementService leaveBalance = new LeaveManagementService() ;
        int satAndSun = leaveBalance.SatAndSun(year, month);
        int days = leaveBalance.DaysOfMonth(year, month);
        List<WorkTimeModel> employeeWorkDays = leaveBalance.getWorkTimeByMonthAndYear(workTimeModels, year, month);
        int ans = leaveBalance.CalculateLeaveBalance(employeeWorkDays, days,  satAndSun);
        return ans ;
    }

}
