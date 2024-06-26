package com.companyA.backend.QualityAssuaranceSystem.service;

import com.companyA.backend.QualityAssuaranceSystem.model.Prototype;
import com.companyA.backend.QualityAssuaranceSystem.model.Test;
import com.companyA.backend.QualityAssuaranceSystem.repository.PrototypeRepository;
import com.companyA.backend.QualityAssuaranceSystem.repository.ReportRepository;
import com.companyA.backend.QualityAssuaranceSystem.repository.TestRepository;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;



@Service
public class PrototypeService {

    @Autowired
    private PrototypeRepository prototypeRepository;
    @Autowired
    private TestRepository testRepository;

    public List<Prototype> getAllPrototypes() {
        return prototypeRepository.findAll();
    }

    public Optional<Prototype> getPrototypeById(String id) {
        return prototypeRepository.findById(id);
    }

    public String createPrototype(Prototype prototype) {
        String tempid = prototype.getId();
        Optional<Prototype> AvalilablePrototype = prototypeRepository.findById(tempid);
        if (AvalilablePrototype.isPresent()) {
            return "The prototype with id: "+tempid +" is already created.";
        }

        else {
            prototype.setTestStatus("Received");
            prototype.setAllocatedTest(null);
            prototypeRepository.save(prototype);
            return "Prototype added Successfully";
        }
    }

    public Prototype addPrototype(Prototype prototype) {
        prototype.setTestStatus("Received");
        prototype.setAllocatedTest(null);
        return prototypeRepository.save(prototype);
    }

    public String test(Prototype prototype,Test test){
        String tempid = prototype.getId();
        String temptestid = test.getTestId();
        Optional<Prototype> AvalilablePrototype = prototypeRepository.findById(tempid);
        Optional<Test> AvalilableTest = testRepository.findById(temptestid);
        if (AvalilablePrototype.isPresent()&&AvalilableTest.isPresent()) {
            String currentStatus = AvalilablePrototype.get().getTestStatus();
            if (currentStatus.equals("Assigened")) {
                prototype.setAllocatedTest(test);
                prototype.setTestStatus("Test initiated");
                prototypeRepository.save(prototype);
                return "The prototype with id: " + tempid + " is subjected to : " + test.getName();
            }
            else return " Previous Test hasn't been finished or a manager hasn't been assigned yet. Test was not initiated " ;
        }

        else {
            return "Invalid parameters. Test was not initiated";
        }

    }


    public String updateTestMethodById(String prototypeId, String newTestName) {
        Prototype outdatedPrototype = prototypeRepository.findById(prototypeId).orElse(null);
        if (outdatedPrototype != null) {
            if (!outdatedPrototype.getTestStatus().equals("Test initiated")) {
                prototypeRepository.deleteById(prototypeId);
                outdatedPrototype.setExpectedTest(newTestName);
                prototypeRepository.save(outdatedPrototype);
                return "Test method successfully changed";
            }
            else return "Testing process already started. Test method cannot be changed";
        }
        else return "invalid request";
    }

    public String deleteById(String id){
        Optional<Prototype> prototype = prototypeRepository.findById(id);
        if (prototype.isPresent()) {
            if (prototype.get().getTestStatus().equals("Received")) {
                prototypeRepository.deleteById(id);
                return "Prototype with ID " + id + " successfully deleted";
            } else {
                return "Prototype with ID " + id + " could not be deleted at the moment";
            }
        } else {
            return "Prototype with ID " + id + " not found";
        }
    }
}

