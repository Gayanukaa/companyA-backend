package com.companyA.backend.TrainingAndDevelopmentSystem.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
@Data
public class Prototyping {
    @Id
    private String id;
    private int prototypeId;
    private String prototypeName;
    private String prototypeType;

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
