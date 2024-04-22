package com.companyA.backend.CustomerOrderSystem.service;
import com.companyA.backend.CustomerOrderSystem.model.Login;
import com.companyA.backend.CustomerOrderSystem.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private LoginRepository loginRepository;

    public void addUser(Login user) {loginRepository.save(user);
    }

}
