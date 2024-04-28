package com.companyA.backend.QualityAssuaranceSystem.contoller;

import com.companyA.backend.QualityAssuaranceSystem.model.Prototype;
import com.companyA.backend.QualityAssuaranceSystem.model.Sample;
import com.companyA.backend.QualityAssuaranceSystem.model.Test;
import com.companyA.backend.QualityAssuaranceSystem.repository.SampleRepository;
import com.companyA.backend.QualityAssuaranceSystem.repository.TestRepository;
import com.companyA.backend.QualityAssuaranceSystem.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/samples")
@CrossOrigin
public class SampleController {

    @Autowired
    private SampleService sampleService;
    @Autowired
    private SampleRepository sampleRepository;
    @Autowired
    private TestRepository testRepository;

    @PostMapping("/addSample")
    @ResponseStatus(HttpStatus.CREATED)
    public Sample addSample(@RequestBody Sample sample) {
        return sampleService.addSample(sample);
    }

    @GetMapping
    public List<Sample> getAllSamples() {
        return sampleService.getAllSamples();
    }

    @GetMapping("/getSample/{id}")
    public Optional<Sample> getSampleById(@PathVariable String id) {
        return sampleService.getSampleById(id);
    }

    @PostMapping("/createsample")
    public String createNewSample(@RequestBody Sample sample) {
        return sampleService.createSample(sample);

    }

    @PutMapping("/inspect")    // Check Api
    @ResponseStatus(HttpStatus.CREATED)
    public String testSample(@RequestParam String sampleId,  String testId) {
        if (sampleRepository.existsById(sampleId)&&testRepository.existsById(testId)) {
            Sample newSample = sampleRepository.findById(sampleId).get();
            Test newTest = testRepository.findById(testId).get();
            return sampleService.testSample(newSample, newTest); // prototype ekt find by id eka dla aye check krnna
        }
        else return "Invalid request";
    }

    @PutMapping ("/changeTest")
    @ResponseStatus(HttpStatus.CREATED)
    public String updateTestMethodById(@RequestParam String sampleId,String newTestName) {
        return sampleService.updateTestMethodById(sampleId, newTestName);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSample(@PathVariable String id){
        return sampleService.deleteById(id);
    }

}
