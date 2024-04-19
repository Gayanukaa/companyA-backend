package com.companyA.backend.TrainingAndDevelopmentSystem.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Training {
    @Id
    private String id;
    private int duration ;
    private double cost;

    public  String enroll(int employeeId){
        return employeeId+" enrolled successfully";
    }

    public String complete(int employeeId){
        return employeeId+ "completed successfully";
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
