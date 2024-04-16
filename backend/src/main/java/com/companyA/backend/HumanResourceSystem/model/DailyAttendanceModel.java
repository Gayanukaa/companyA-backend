package com.companyA.backend.HumanResourceSystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "dailyAttendance")

public class DailyAttendanceModel {
    @Id
    private String id;
    private String employeeId;
    private LocalDate date;
    private LocalDateTime signInTime;
    private LocalDateTime signOutTime;
    private boolean isPresent;


   // private AttendanceIdModel employeeId;

}
