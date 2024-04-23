package com.companyA.backend.HumanResourceSystem.service;

//import com.companyA.backend.HumanResourceSystem.model.PayrollModel;
import com.companyA.backend.HumanResourceSystem.model.DailyAttendanceModel;
import com.companyA.backend.HumanResourceSystem.repository.DailyAttendanceRepository;
//import com.companyA.backend.HumanResourceSystem.repository.PayRollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class PayRollService {

    @Autowired
    //PayRollRepository payRollRepository;
    DailyAttendanceRepository dailyAttendanceRepository;

    public String calculateWorkHours(List<DailyAttendanceModel> workTimeList) {
        long workMinutes = 0;

        for (DailyAttendanceModel workTime : workTimeList) {
            LocalDateTime signInTime = workTime.getSignInTime();
            LocalDateTime signOutTime = workTime.getSignOutTime();

            if (signInTime != null && signOutTime != null) {
                long minutes = ChronoUnit.MINUTES.between(signInTime, signOutTime);

                workMinutes += minutes ;

            }
        }

        long wHours = workMinutes / 60;
        long wMinutes = workMinutes % 60;



        return "Total worked time: "+ wHours + " hours " +   wMinutes + " minutes" ;

    }


    public String calculaOtHours(List<DailyAttendanceModel> workTimeList) {
        long workMinutes = 0;
        long otMinutes = 0;

        for (DailyAttendanceModel workTime : workTimeList) {
            LocalDateTime signInTime = workTime.getSignInTime();
            LocalDateTime signOutTime = workTime.getSignOutTime();

            if (signInTime != null && signOutTime != null) {
                long minutes = ChronoUnit.MINUTES.between(signInTime, signOutTime);
                if (minutes > 8*60) {
                    workMinutes += 8*60 ;
                    otMinutes += minutes - 8*60;
                }
                else {
                    workMinutes += minutes;
                }
            }
        }


        long hoursOt = otMinutes / 60;
        long minutesOt = otMinutes % 60;


        return "Total OT time: " + hoursOt + " Hours " + minutesOt + " minutes" ;

    }


}


