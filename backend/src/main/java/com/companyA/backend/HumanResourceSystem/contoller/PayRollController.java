package com.companyA.backend.HumanResourceSystem.contoller;

import com.companyA.backend.HumanResourceSystem.model.EmployeeDetailModel;
import com.companyA.backend.HumanResourceSystem.model.PayrollModel;
import com.companyA.backend.HumanResourceSystem.model.DailyAttendanceModel;
import com.companyA.backend.HumanResourceSystem.repository.PayRollRepository;
import com.companyA.backend.HumanResourceSystem.service.PayRollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;


@Controller
@RequestMapping (value = "/PayRoll")
public class PayRollController {


    @Autowired
    PayRollRepository payRollRepository;
    PayRollService payRollService;

    @GetMapping("/WorkTime/WorkHours")

        public ResponseEntity<String> getEmployeeWorkHours(@RequestParam String uid,
                                                                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
            List<DailyAttendanceModel> workTimeModels = payRollRepository.findByUidAndDateBetween(uid , startDate, endDate);

            PayRollService payRoll1 = new PayRollService() ;
            String ans = payRoll1.calculateWorkHours(workTimeModels) ;

            if (workTimeModels.isEmpty()) {
                return ResponseEntity.ok("User did not work on that time period");
            }
            return ResponseEntity.ok(ans);
        }

    @GetMapping("/WorkTime/WorkOtHours")

    public ResponseEntity<String> getEmployeeOtHours(@RequestParam String uid,
                                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<DailyAttendanceModel> workTimeModels = payRollRepository.findByUidAndDateBetween(uid , startDate, endDate);

        PayRollService payRoll1 = new PayRollService() ;
        String ans = payRoll1.calculaOtHours(workTimeModels) ;

        if (workTimeModels.isEmpty()) {
            return ResponseEntity.ok("User did not work on that time period");
        }
        return ResponseEntity.ok(ans);
    }


}




