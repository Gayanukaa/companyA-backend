package com.companyA.backend.QualityAssuaranceSystem.contoller;

import com.companyA.backend.QualityAssuaranceSystem.model.Prototype;
import com.companyA.backend.QualityAssuaranceSystem.model.QualityAssuaranceManager;
import com.companyA.backend.QualityAssuaranceSystem.model.Sample;
import com.companyA.backend.QualityAssuaranceSystem.model.TestSubjects;
import com.companyA.backend.QualityAssuaranceSystem.repository.PrototypeRepository;
import com.companyA.backend.QualityAssuaranceSystem.repository.SampleRepository;
import com.companyA.backend.QualityAssuaranceSystem.service.PrototypeService;
import com.companyA.backend.QualityAssuaranceSystem.service.QualityAssuaranceManagerService;
import com.companyA.backend.QualityAssuaranceSystem.service.SampleService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/qaManager")
@CrossOrigin
public class QualityAssuaranceManagerController {

    @Autowired
    private QualityAssuaranceManagerService qualityAssuaranceManagerService;
    @Autowired
    private PrototypeService prototypeService;
    @Autowired
    private SampleService sampleService;
    @Autowired
    private PrototypeRepository prototypeRepository;
    @Autowired
    private SampleRepository sampleRepository;
    @Autowired
    private View error;

    @PostMapping("/addQAManager")
    @ResponseStatus(HttpStatus.CREATED)
    public QualityAssuaranceManager addNewQAManager(@RequestBody QualityAssuaranceManager qualityAssuaranceManager) {
        return qualityAssuaranceManagerService.addNewQAManager(qualityAssuaranceManager);
    }

    @GetMapping
    public List<QualityAssuaranceManager> getAllQAManagers() {
        return qualityAssuaranceManagerService.getAllQAManagers();
    }

    @GetMapping("/getQAManager/{id}")
    public Optional<QualityAssuaranceManager> getQAManagerById(@RequestParam("id") String id) {
        return qualityAssuaranceManagerService.getQAManagerById(id);
    }

    @GetMapping("/notCheckedItems")
    public List<TestSubjects> notCheckedTestSubjects() {
        List<? extends TestSubjects> allPrototypes = prototypeService.getAllPrototypes();
        List<? extends TestSubjects> allSamples = sampleService.getAllSamples();
        List<TestSubjects> notCheckedTestItems = new ArrayList<>(200);
        if (!allPrototypes.isEmpty()||!allSamples.isEmpty()) {
            notCheckedTestItems.addAll(qualityAssuaranceManagerService.notCheckedTestSubjects((List<TestSubjects>) allPrototypes));
            notCheckedTestItems.addAll(qualityAssuaranceManagerService.notCheckedTestSubjects((List<TestSubjects>) allSamples));
            return notCheckedTestItems;
        }
        else return null ;
    }

    @SneakyThrows
    @PutMapping("/concludeTest")
    public String concludeTest(@RequestParam String qaManagerId,String testSubjectId) {

        Optional<QualityAssuaranceManager> qaManager = qualityAssuaranceManagerService.getQAManagerById(qaManagerId);

        Optional<Prototype> foundPrototype = prototypeService.getPrototypeById(testSubjectId);
        boolean prototypeStatus = false;
        if (foundPrototype.isPresent()) {
            prototypeStatus = foundPrototype.get().getTestStatus().equals("Test initiated");
        }

        Optional<Sample> foundSample = sampleService.getSampleById(testSubjectId);
        boolean sampleStatus = false;
        if (foundSample.isPresent()) {
            sampleStatus = foundSample.get().getTestStatus().equals("Test initiated");
        }

        if (qaManager.isPresent() && prototypeStatus) {
            TestSubjects checkedPrototypeAsTestSubject = qualityAssuaranceManagerService.concludeTest(qaManager.get(), testSubjectId);
            Prototype checkedPrototype = prototypeRepository.findById(checkedPrototypeAsTestSubject.getId()).orElse(null);
            prototypeRepository.deleteById(Objects.requireNonNull(checkedPrototype).getId());
            checkedPrototype.setTestStatus("Done by " + qaManager.get().getName());
            prototypeRepository.save(checkedPrototype);
            return "Test was concluded successfully";
        } else if (qaManager.isPresent() && sampleStatus) {
            TestSubjects checkedSampleAsTestSubject = qualityAssuaranceManagerService.concludeTest(qaManager.get(), testSubjectId);
            Sample checkedSample = sampleRepository.findById(checkedSampleAsTestSubject.getId()).orElse(null);
            sampleRepository.deleteById(Objects.requireNonNull(checkedSample).getId());
            checkedSample.setTestStatus("Done by " + qaManager.get().getName());
            sampleRepository.save(checkedSample);
            return "Test was concluded successfully";
        } else throw (Throwable) error;
    }

    @SneakyThrows
    @PutMapping("/assignManager")
    public String assignManager(@RequestParam String qaManagerId,String testSubjectId) {
        Optional<Prototype> foundPrototype = prototypeService.getPrototypeById(testSubjectId);
        Optional<Sample> foundSample = sampleService.getSampleById(testSubjectId);
        if (foundPrototype.isPresent()){
            String response = qualityAssuaranceManagerService.assignManager(qaManagerId, foundPrototype.get());
            if (!(response == "Invalid Request")) {
                prototypeRepository.deleteById(foundPrototype.get().getId());
                foundPrototype.get().setTestStatus("Assigened");
                prototypeRepository.save(foundPrototype.get());
                return response;
            }
            else return response;
        }
        else if (foundSample.isPresent()){
            String response = qualityAssuaranceManagerService.assignManager(qaManagerId, foundSample.get());
            if (!(response == "Invalid Request")) {
                sampleRepository.deleteById(foundSample.get().getId());
                foundSample.get().setTestStatus("Assigened");
                sampleRepository.save(foundSample.get());
                return response;
            }
            else return response;
        }
        else throw (Throwable) error;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteQAManager(@PathVariable String id){
        return qualityAssuaranceManagerService.deleteQAManagerById(id);
    }

}
//Api checked by Ushan
