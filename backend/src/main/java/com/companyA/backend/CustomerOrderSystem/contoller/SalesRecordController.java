package com.companyA.backend.CustomerOrderSystem.contoller;
import java.util.Optional;
import com.companyA.backend.CustomerOrderSystem.model.SalesRecord;
import com.companyA.backend.CustomerOrderSystem.service.SalesRecordService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class SalesRecordController {
    @Autowired
    private SalesRecordService salesRecordService;

    @GetMapping("/salesRecord")
    public ResponseEntity<Optional<SalesRecord>> getOrderHistory(@RequestParam("user_ID") ObjectId user_ID) {
        Optional<SalesRecord> response = salesRecordService.getOrderHistory(user_ID);
        return new ResponseEntity<Optional<SalesRecord>>(response, HttpStatus.OK);
    }
}
