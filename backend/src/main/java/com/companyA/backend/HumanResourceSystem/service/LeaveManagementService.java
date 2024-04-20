package com.companyA.backend.HumanResourceSystem.service;

import com.companyA.backend.HumanResourceSystem.model.DailyAttendanceModel;
import com.companyA.backend.HumanResourceSystem.repository.DailyAttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service
public class LeaveManagementService {
    @Autowired
    DailyAttendanceRepository dailyAttendanceRepository;

    public int CalculateLeaveBalance (List<DailyAttendanceModel> attendanceList, int daysOfMonth, int satAndSun){
        int allocatedLeaves = 3 ;   // allocated leaves per employee
        int workedDays = attendanceList.size() ;
        int leaveBalance = daysOfMonth - satAndSun - workedDays;
        return allocatedLeaves - leaveBalance;

    }

    public int SatAndSun (int year , int month){
        int numberOfSaturdays = 0;
        int numberOfSundays = 0;
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        LocalDate lastDayOfMonth = firstDayOfMonth.withDayOfMonth(firstDayOfMonth.lengthOfMonth());

        for (LocalDate date = firstDayOfMonth; !date.isAfter(lastDayOfMonth); date = date.plusDays(1)) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            if (dayOfWeek == DayOfWeek.SATURDAY) {
                numberOfSaturdays++;
            } else if (dayOfWeek == DayOfWeek.SUNDAY) {
                numberOfSundays++;
            }
        }
        return numberOfSaturdays +numberOfSundays ;
    }


    public int DaysOfMonth (int year, int month){
        YearMonth yearMonth = YearMonth.of(year, month);

        return yearMonth.lengthOfMonth();
    }

    public List<DailyAttendanceModel> getWorkTimeByMonthAndYear(List<DailyAttendanceModel> workTimeList, int year, int month) {
        List<DailyAttendanceModel> filteredList = new ArrayList<>();

        for (DailyAttendanceModel workTime : workTimeList) {
            if (workTime.getDate().getMonthValue() == month ) {
                filteredList.add(workTime);
            }
        }
        return filteredList ;
    }
}
