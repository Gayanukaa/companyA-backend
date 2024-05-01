package com.companyA.backend;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.util.Arrays;

public class WelcomeRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("=====================================");
        System.out.println("   Welcome to Company A Backend!   ");
        System.out.println("=====================================");
        // System.out.println("  - Active Profiles: " + Arrays.toString(args.getSourceArgs()));
    }
}
