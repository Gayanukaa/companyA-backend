package com.companyA.backend.HumanResourceSystem.service;

import com.companyA.backend.HumanResourceSystem.model.WorkTimeModel;
import com.companyA.backend.HumanResourceSystem.repository.LeaveManagementRepository;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service
public class LeaveManagementService {
    @Autowired
    static LeaveManagementRepository leaveManagementRepository;

    public int CalculateLeaveBalance (List<WorkTimeModel> attendanceList, int daysOfMonth, int satAndSun){
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

    public List<WorkTimeModel> getWorkTimeByMonthAndYear(List<WorkTimeModel> workTimeList, int year, int month) {
        List<WorkTimeModel> filteredList = new ArrayList<>();

        for (WorkTimeModel workTime : workTimeList) {
            if (workTime.getDate().getMonthValue() == month ) {
                filteredList.add(workTime);
            }
        }
        return filteredList ;
    }
}
