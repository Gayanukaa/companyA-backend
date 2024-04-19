package com.companyA.backend.TrainingAndDevelopmentSystem.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "MainSupervisor")
@Data
public class MainSupervisor {
    @Id
    private String id;
    private int mSupervisorId;
    private String mSupervisorName;
    private String mPassword;

    public void viewDevelopmentStage(int projectCode, int stage){
        System.out.println(projectCode+" ,"+stage+" monitored successfully");
    }

    public void takeActionsManually(int projectCode,boolean check){
        if (check){
            System.out.println(projectCode+" manually checked complete");
        }
        else{
            System.out.println("waiting for check");
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getmSupervisorId() {
        return mSupervisorId;
    }

    public void setmSupervisorId(int mSupervisorId) {
        this.mSupervisorId = mSupervisorId;
    }

    public String getmSupervisorName() {
        return mSupervisorName;
    }

    public void setmSupervisorName(String mSupervisorName) {
        this.mSupervisorName = mSupervisorName;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }
}
