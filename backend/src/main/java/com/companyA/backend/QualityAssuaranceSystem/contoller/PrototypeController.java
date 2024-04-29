package com.companyA.backend.QualityAssuaranceSystem.contoller;

import com.companyA.backend.QualityAssuaranceSystem.model.Prototype;
import com.companyA.backend.QualityAssuaranceSystem.model.Test;
import com.companyA.backend.QualityAssuaranceSystem.repository.PrototypeRepository;
import com.companyA.backend.QualityAssuaranceSystem.repository.TestRepository;
import com.companyA.backend.QualityAssuaranceSystem.service.PrototypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/prototypes")
@CrossOrigin
public class PrototypeController {

    @Autowired
    private PrototypeService prototypeService;
    @Autowired
    private PrototypeRepository prototypeRepository;
    @Autowired
    private TestRepository testRepository;

    @PostMapping("/addPrototype")
    @ResponseStatus(HttpStatus.CREATED)
    public Prototype addPrototype(@RequestBody Prototype prototype) {
        return prototypeService.addPrototype(prototype);
    }

    @GetMapping("/getAllPrototypes")
    public List<Prototype> getAllPrototypes() {
        return prototypeService.getAllPrototypes();
    }

    @GetMapping("/getPrototype/{id}")
    public Optional<Prototype> getPrototypeById(@RequestParam("id") String id) {
        return prototypeService.getPrototypeById(id);
    }

    @PostMapping("/createprototype")
    public String createNewPrototype(@RequestBody Prototype prototype) {
        return prototypeService.createPrototype(prototype);

    }

    @PutMapping("/inspect")    // Check Api
    @ResponseStatus(HttpStatus.CREATED)
    public String testNewPrototype(@RequestParam String prototypeId,  String testId) {
        if (prototypeRepository.existsById(prototypeId)&&testRepository.existsById(testId)) {
            Prototype newPrototype = prototypeRepository.findById(prototypeId).get();
            Test newTest = testRepository.findById(testId).get();
            return prototypeService.testPrototype(newPrototype, newTest); // prototype ekt find by id eka dla aye check krnna
        }
        else return "Invalid request";
    }

    @PutMapping ("/changeTest")
    @ResponseStatus(HttpStatus.CREATED)
    public String updateTestMethodById(@RequestParam String prototypeId,String newTestName) {
         return prototypeService.updateTestMethodById(prototypeId, newTestName);
    }

    @DeleteMapping("/delete/{id}")
    public String deletePrototype(@PathVariable String id){
        return prototypeService.deleteById(id);
    }
}
