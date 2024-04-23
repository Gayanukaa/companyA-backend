package com.companyA.backend.TrainingAndDevelopmentSystem.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Prototyping")
public class Prototyping {
    @Id
    private String id;
    private int prototypeId;
    private String prototypeName;
    private String prototypeType;

    // Here, I have implemented getter and setter methods instead of lombok annotations



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPrototypeType() {
        return prototypeType;
    }

    public void setPrototypeType(String prototypeType) {
        this.prototypeType = prototypeType;
    }
}
