package com.companyA.backend.TrainingAndDevelopmentSystem.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class MainSupervisor {
    @Id
    private String id;
    private int mSupervisorId;
    private String mSupervisorName;
    private String mPassword;

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
