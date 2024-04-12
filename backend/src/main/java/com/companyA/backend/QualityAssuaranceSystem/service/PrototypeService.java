package com.companyA.backend.QualityAssuaranceSystem.service;

import com.companyA.backend.QualityAssuaranceSystem.model.Prototype;
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
    private PrototypeRepository repository;

    public List<Prototype> getAllPrototypes() {
        return repository.findAll();
    }

    public Optional<Prototype> getPrototypeById(String id) {
        return repository.findById(id);
    }

    public Prototype addPrototype(Prototype prototype) {
        return repository.save(prototype);
    }

}
