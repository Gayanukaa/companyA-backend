package com.companyA.backend.TrainingAndDevelopmentSystem.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
@Data
public class ProductDevelopment {
    @Id
    private String id;
    private int projectCode;
    private String projectName;
    private String projectManager;
    private int prototypeId;
    private String prototypeName;
    private boolean stageOne;
    private boolean stageTwo;
    private boolean stageThree;
    private int stageCompleted;
    private int progress;
    private String prototypeType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(int projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public int getPrototypeId() {
        return prototypeId;
    }

    public void setPrototypeId(int prototypeId) {
        this.prototypeId = prototypeId;
    }

    public String getPrototypeName() {
        return prototypeName;
    }

    public void setPrototypeName(String prototypeName) {
        this.prototypeName = prototypeName;
    }

    public boolean isStageOne() {
        return stageOne;
    }

    public void setStageOne(boolean stageOne) {
        this.stageOne = stageOne;
    }

    public boolean isStageTwo() {
        return stageTwo;
    }

    public void setStageTwo(boolean stageTwo) {
        this.stageTwo = stageTwo;
    }

    public boolean isStageThree() {
        return stageThree;
    }

    public void setStageThree(boolean stageThree) {
        this.stageThree = stageThree;
    }

    public int getStageCompleted() {
        return stageCompleted;
    }

    public void setStageCompleted(int stageCompleted) {
        this.stageCompleted = stageCompleted;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getPrototypeType() {
        return prototypeType;
    }

    public void setPrototypeType(String prototypeType) {
        this.prototypeType = prototypeType;
    }
}
