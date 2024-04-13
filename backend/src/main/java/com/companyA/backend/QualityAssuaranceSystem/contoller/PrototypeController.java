package com.companyA.backend.QualityAssuaranceSystem.contoller;

import com.companyA.backend.QualityAssuaranceSystem.model.Prototype;
import com.companyA.backend.QualityAssuaranceSystem.model.Test;
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
    private PrototypeService prototypeService;

    @PostMapping("/addPrototype")
    @ResponseStatus(HttpStatus.CREATED)
    public Prototype addPrototype(@RequestBody Prototype prototype) {
        return prototypeService.addPrototype(prototype);
    }

    @GetMapping("/getAllPrototypes")
    public List<Prototype> getAllPrototypes() {
        return prototypeService.getAllPrototypes();
    }

    @GetMapping("/getPrototype/{id}")   // could not check with postman
    public Optional<Prototype> getPrototypeById(@PathVariable String id) {
        return prototypeService.getPrototypeById(id);
    }

    @PostMapping("/createprototype")
    public String createNewPrototype(@RequestBody Prototype prototype) {
        return prototypeService.createPrototype(prototype);

    }

    @PostMapping("/inspect")
    @ResponseStatus(HttpStatus.CREATED)
    public String testNewPrototype(@RequestBody Prototype prototype, @RequestBody Test test) {
        String response = prototypeService.testPrototype(prototype, test); // prototype ekt find by id eka dla aye check krnna
        prototype.setTestStatus("Test Initiated");
        return response;
    }

}
