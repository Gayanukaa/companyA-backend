package com.companyA.backend.CustomerOrderSystem.contoller;
import com.companyA.backend.CustomerOrderSystem.model.Login;
import com.companyA.backend.CustomerOrderSystem.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/addUser")
    public ResponseEntity<Map<String, String>> userLogin(@RequestBody Login user) {
        loginService.addUser(user);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Login information sent successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}