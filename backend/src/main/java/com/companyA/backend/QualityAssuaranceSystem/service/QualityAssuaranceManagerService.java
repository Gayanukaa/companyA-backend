package com.companyA.backend.QualityAssuaranceSystem.service;

import com.companyA.backend.QualityAssuaranceSystem.model.QualityAssuaranceManager;
import com.companyA.backend.QualityAssuaranceSystem.model.TestSubjects;
import com.companyA.backend.QualityAssuaranceSystem.repository.QualityAssuaranceManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QualityAssuaranceManagerService {

    @Autowired
    private QualityAssuaranceManagerRepository qualityAssuaranceManagerRepository;

    public List<QualityAssuaranceManager> getAllQAManagers() {
        return qualityAssuaranceManagerRepository.findAll();
    }

    public Optional<QualityAssuaranceManager> getQAManagerById(String id) {
        return qualityAssuaranceManagerRepository.findById(id);
    }

    public QualityAssuaranceManager addNewQAManager(QualityAssuaranceManager qualityAssuaranceManager) {
        List<TestSubjects> newList = new ArrayList<>(100) ;
        qualityAssuaranceManager.setAssignedTestSubjects(newList);
        return qualityAssuaranceManagerRepository.save(qualityAssuaranceManager);
    }

    public List<TestSubjects> notCheckedTestSubjects(List<TestSubjects> testSubjects) {
        List<TestSubjects> notCheckedTestSubjects = new ArrayList<>(100);
        for (TestSubjects testSubject : testSubjects) {
            if (testSubject.getTestStatus().equals("Received")) {
                notCheckedTestSubjects.add(testSubject);
            }
        }
        return notCheckedTestSubjects;
    }

    public TestSubjects concludeTest(QualityAssuaranceManager qaManager,String testSubjectId) {
        List<TestSubjects> assignedTestSubjects;
        assignedTestSubjects = qaManager.getAssignedTestSubjects();
        for (TestSubjects testSubject : assignedTestSubjects) {
            if (testSubject.getId().equals(testSubjectId)) {
                testSubject.setTestStatus("Checked");
                assignedTestSubjects.remove(testSubject);
                qaManager.setAssignedTestSubjects(assignedTestSubjects);
                qualityAssuaranceManagerRepository.deleteById(qaManager.getId());
                qualityAssuaranceManagerRepository.save(qaManager);
                return testSubject;
            }
        }
        return null;
    }

    public String assignManager(String qaManagerId,TestSubjects testSubject) {
        Optional<QualityAssuaranceManager> qaManager = getQAManagerById(qaManagerId);
        if (qaManager.isPresent()&&testSubject.getTestStatus().equals("Received")) {
            qaManager.get().getAssignedTestSubjects().add(testSubject);
            qualityAssuaranceManagerRepository.deleteById(qaManagerId);
            qualityAssuaranceManagerRepository.save(qaManager.get());
            return "Test Subject " + testSubject.getId() + " was Assigned to " + qaManager.get().getName();
        }
        else return "Invalid Request";
    }

    public String deleteQAManagerById(String id) {
        Optional<QualityAssuaranceManager> qaManager = qualityAssuaranceManagerRepository.findById(id);
        if (qaManager.isPresent()) {
            qualityAssuaranceManagerRepository.deleteById(id);
            return "QA manager with ID " + id + " successfully removed";
        } else {
            return "QA manager with ID " + id + " not found";
        }
    }
}
