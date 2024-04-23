package com.companyA.backend.TrainingAndDevelopmentSystem.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Document(collection = "TrainingEmployee")
public class TrainingEmployee {
    @Id
    private String id;
    private int employeeId;
    private String name;
    private String employeeEmail;
    private String password;

    private List<Long> enrolledCourses;

    private List<Long> completedCourses;

    // Here, I have implemented getter and setter methods, constructors instead of lombok annotations

    public TrainingEmployee() {
        this.enrolledCourses = new ArrayList<>();
        this.completedCourses = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return employeeEmail;
    }

    public void setEmail(String email) {
        this.employeeEmail = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Long> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<Long> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public List<Long> getCompletedCourses() {
        return completedCourses;
    }

    public void setCompletedCourses(List<Long> completedCourses) {
        this.completedCourses = completedCourses;
    }
}
