package com.companyA.backend.CustomerOrderSystem.contoller;
import java.util.Optional;
import com.companyA.backend.CustomerOrderSystem.model.UserDetails;
import com.companyA.backend.CustomerOrderSystem.service.UserDetailsService;
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
public class UserDetailsController {
    @Autowired
    private UserDetailsService salesRecordService;

    @GetMapping("/userDetails")
    public ResponseEntity<Optional<UserDetails>> getOrderHistory(@RequestParam("user_ID") ObjectId user_ID) {
        Optional<UserDetails> response = salesRecordService.getUserDetails(user_ID);
        return new ResponseEntity<Optional<UserDetails>>(response, HttpStatus.OK);
    }
}
