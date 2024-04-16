package com.companyA.backend.QualityAssuaranceSystem.contoller;

import com.companyA.backend.QualityAssuaranceSystem.model.Prototype;
import com.companyA.backend.QualityAssuaranceSystem.model.Sample;
import com.companyA.backend.QualityAssuaranceSystem.model.Test;
import com.companyA.backend.QualityAssuaranceSystem.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//Make necessary changes

@RestController
@RequestMapping("/api/v1/samples")
public class SampleController {
    @Autowired
    private SampleService sampleService;

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

    @PostMapping("/inspect")
    @ResponseStatus(HttpStatus.CREATED)
    public String testNewSample(@RequestBody Sample sample,@RequestBody Test test) {
        String response =sampleService.testSample(sample,test);
        sample.setTestStatus("Test Initiated");
        return response;
    }

    @PostMapping("/generateReport")
    @ResponseStatus(HttpStatus.CREATED)
    public String generateReport() {
        return sampleService.generateReport();
    }
}
