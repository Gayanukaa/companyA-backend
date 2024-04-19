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
    private String mSupervisorEmail;
    private String mPassword;

    public String getmSupervisorEmail() {
        return mSupervisorEmail;
    }

    public void setmSupervisorEmail(String mSupervisorEmail) {
        this.mSupervisorEmail = mSupervisorEmail;
    }

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



    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }
}
