package com.companyA.backend.TrainingAndDevelopmentSystem.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UnitSupervisor")
@Data
public class UnitSupervisor {
    @Id
    private String id;
    private int uSupervisorId;
    private String uSupervisorEmail;
    private String uPassword;

    public void updateStage(int projectcode, int stage){
        System.out.println(projectcode+", "+stage+" completed successfully");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getuSupervisorId() {
        return uSupervisorId;
    }

    public void setuSupervisorId(int uSupervisorId) {
        this.uSupervisorId = uSupervisorId;
    }

    public String getuSupervisorEmail() {
        return uSupervisorEmail;
    }

    public void setuSupervisorEmail(String uSupervisorEmail) {
        this.uSupervisorEmail = uSupervisorEmail;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }
}
