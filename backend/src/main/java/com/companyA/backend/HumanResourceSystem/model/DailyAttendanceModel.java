package com.companyA.backend.HumanResourceSystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "dailyAttendance")

public class DailyAttendanceModel {
    private String id;
    private LocalDate date;
    private LocalDate signInTime;
    private LocalDate signOutTime;

}
