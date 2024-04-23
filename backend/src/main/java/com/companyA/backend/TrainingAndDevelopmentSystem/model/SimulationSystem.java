package com.companyA.backend.TrainingAndDevelopmentSystem.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class SimulationSystem {
    private int systemId;
    private String systemName;
    private String simulationType;

    //need to implement simulateSystem method
    //need to implement analyzeResults method
    // this model will not deeply implement as it not need to use case scenarios
    // Here, I have implemented getter and setter methods instead of lombok annotations
    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getSimulationType() {
        return simulationType;
    }

    public void setSimulationType(String simulationType) {
        this.simulationType = simulationType;
    }
}
