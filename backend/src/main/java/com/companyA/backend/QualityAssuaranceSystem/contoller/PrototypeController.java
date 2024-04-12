package com.companyA.backend.QualityAssuaranceSystem.contoller;

import com.companyA.backend.QualityAssuaranceSystem.model.Prototype;
import com.companyA.backend.QualityAssuaranceSystem.service.PrototypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//Make necessary changes

@RestController
@RequestMapping("/api/v1/prototypes")
public class PrototypeController {

    @Autowired
    private PrototypeService service;

    @PostMapping("/addPrototype")
    @ResponseStatus(HttpStatus.CREATED)
    public Prototype addPrototype(@RequestBody Prototype prototype) {
        return service.addPrototype(prototype);
    }

    @GetMapping
    public List<Prototype> getAllPrototypes() {
        return service.getAllPrototypes();
    }

    @GetMapping("/getPrototype/{id}")
    public Optional<Prototype> getPrototypeById(@PathVariable String id) {
        return service.getPrototypeById(id);
    }
}
