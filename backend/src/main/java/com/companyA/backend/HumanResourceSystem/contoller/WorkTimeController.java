package com.companyA.backend.HumanResourceSystem.contoller;

import com.companyA.backend.HumanResourceSystem.model.EmployeeDetailModel;
import com.companyA.backend.HumanResourceSystem.model.WorkTimeModel;
import com.companyA.backend.HumanResourceSystem.repository.WorkTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/WorkTime")

public class WorkTimeController {

    @Autowired
    WorkTimeRepository workTimeRepository;

    @PostMapping("/add")
    public ResponseEntity<List<WorkTimeModel>> insertWorkTime(@RequestBody List<WorkTimeModel> workTimeModels) {
        for (WorkTimeModel workTimeModel : workTimeModels) {
            List<WorkTimeModel> existingWorkTime = workTimeRepository.findByIdAndDate(workTimeModel.getId(), workTimeModel.getDate());

            if (existingWorkTime.isEmpty()) {
                workTimeRepository.save(workTimeModel);
            }
            else {
                WorkTimeModel existingEntry = existingWorkTime.get(0); // Assuming only one entry for simplicity

                // Update the existing entry with data from the new entry
                existingEntry.setTimeSignedIn(workTimeModel.getTimeSignedIn());
                existingEntry.setTimeSignedOut(workTimeModel.getTimeSignedOut());
            }
        }
        return new ResponseEntity<>(workTimeModels, HttpStatus.CREATED);
    }
}