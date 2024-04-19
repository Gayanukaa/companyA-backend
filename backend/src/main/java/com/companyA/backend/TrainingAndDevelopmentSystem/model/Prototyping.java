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

    public void createPrototype(int prototypeId){
        System.out.println(prototypeId+ " created successfully");
    }

    public void testPrototype(int prototypeId, int test_score){
        if (test_score>5 && test_score<10){
            System.out.println(prototypeId+ " ,prototype tested successfully");
        } else if (test_score>0 && test_score<5) {
            System.out.println(prototypeId+ " ,prototype test failed");
        }
        else{
            System.out.println("Test Score Invalid!");
        }
    }

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
