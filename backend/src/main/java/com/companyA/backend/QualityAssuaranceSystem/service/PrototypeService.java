package com.companyA.backend.QualityAssuaranceSystem.service;

import com.companyA.backend.QualityAssuaranceSystem.model.Prototype;
import com.companyA.backend.QualityAssuaranceSystem.model.Test;
import com.companyA.backend.QualityAssuaranceSystem.repository.PrototypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Only a few methods have been declared so far
//Make necessary changes

@Service
public class PrototypeService {

    @Autowired
    private PrototypeRepository prototypeRepository;

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
            prototypeRepository.save(prototype);
            return "Prototype added Successfully";
        }
    }

           // creating and adding a prototype is slightly different
    public Prototype addPrototype(Prototype prototype) {
        return prototypeRepository.save(prototype);
    }

    public String testPrototype(Prototype prototype,Test test){
        String tempid = prototype.getId();
        Optional<Prototype> AvalilablePrototype = prototypeRepository.findById(tempid);
        if (AvalilablePrototype.isPresent()) {
            prototype.setAllocatedTest(test);
            prototypeRepository.save(prototype);
            return "The prototype with id: "+tempid +" is subjected to test:"+ test.getTestId();
        }

        else {
            return "Test was not initiated";
        }

    }

}
