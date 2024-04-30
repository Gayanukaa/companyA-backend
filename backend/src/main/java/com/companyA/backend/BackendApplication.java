package com.companyA.backend;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableMongoAuditing
public class BackendApplication {

	@Bean
	public ApplicationRunner welcomeRunner() {
		return new WelcomeRunner();
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@GetMapping("/")
	public String apiRoot(){
		String line1 = "Welcome to the Company A Backend";
		String linel = "Available Systems:";
		String line2 = "1. Inventory Management System";
		//: <a href='/api/v1/inventory'>/api/v1/inventory</a>"
		String line3 = "2. General Management System";
		String line4 = "3. Customer Order System";
		String line5 = "4. Finance System";
		String line6 = "5. Human Resource System";
		String line7 = "6. Logistics and Maintenance System";
		String line8 = "7. Manufacturing System";
		String line9 = "8. Quality Assurance System";
		String line10 = "9. Sales System";
		String line11 = "10. Training and Development System";
		return line1 + "<br>" + "<br>"+ linel + "<br>" + line2 + "<br>" + line3 + "<br>" + line4 + "<br>" + line5 + "<br>" + line6 + "<br>" + line7 + "<br>" + line8 + "<br>" + line9 + "<br>" + line10 + "<br>" + line11;
	}

}
