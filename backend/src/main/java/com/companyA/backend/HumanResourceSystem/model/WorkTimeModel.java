package com.companyA.backend.HumanResourceSystem.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "WorkTime")

public class WorkTimeModel {
    private String id;
    private LocalDate date;
    private LocalDateTime timeSignedIn ;
    private LocalDateTime timeSignedOut;
}
