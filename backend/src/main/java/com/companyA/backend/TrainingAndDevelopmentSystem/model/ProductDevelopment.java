package com.companyA.backend.TrainingAndDevelopmentSystem.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "ProductDevelopment")
public class ProductDevelopment {
    @Id
    private String id;
    private int projectCode;
    private String projectName;
    private String projectManager;
    private boolean stageOne;
    private boolean stageTwo;
    private boolean stageThree;
    private int stageCompleted;
    private int progress;


    private int getComplexityValue(String complexity) {
        switch (complexity.toLowerCase()) {
            case "low":
                return 1;
            case "medium":
                return 3;
            case "high":
                return 5;
            default:
                return 0;
        }
    }

    public void developProduct(int projectCode, String complexity) {
        int complexScale=getComplexityValue(complexity);
        System.out.println(projectCode+", "+" add to develop successfully with complex scale "+ complexScale);
    }

    public void improveProcess(int pastDevelopmentTime,double improvementPercentage) {

        int suggestedImprovement = (int) (pastDevelopmentTime * improvementPercentage);

        System.out.println("Analyzing development process for project:");
        System.out.println("Past development time: " + pastDevelopmentTime + " hours");
        System.out.println("Suggested improvement: " + suggestedImprovement + " hours");
    }

    public void notifyQA(int projectCode) {
        System.out.println("QA team notified for project: " + projectCode);
    }

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


}
