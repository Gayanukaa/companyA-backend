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
		String meesage = "<b>Welcome to the <a href ='https://github.com/Gayanukaa/companyA-backend'>Company A Backend</a></b>" +
				"<br>" +
				"<br>" +
				"This is project hosts the backend of the electronic warehouse management system with the use of Spring Boot and MongoDB database to create a full-stack web application. The application allows users to manage the company under 10 different subsystems. "+
				"The backend is hosted on a apache server using app service. The application is licensed under the Apache License 2.0. For more information, please visit the GitHub repository." +
				"<br>" +
				"<br>" +
				"The following are the 10 subsystems of the Company A Backend:" +
				"<br>" +
				"<ol>" +
				"<li>Inventory Management System </li>" + //: <a href='/api/v1/inventory'>/api/v1/inventory</a>"
				"<li>General Management System </li>" +
				"<li>Customer Order System </li>" +
				"<li>Finance System </li>" +
				"<li>Human Resource System </li>" +
				"<li>Logistics and Maintenance System </li>" +
				"<li>Manufacturing System </li>" +
				"<li>Quality Assurance System </li>" +
				"<li>Sales System </li>" +
				"<li>Training and Development System </li>" +
				"</ol>" +
				"<b> Overview</b> <br>" +
				"<p>" +
				"Developed with the software and tools below.<br>" +
				"</p>" +
				"<p>" +
				" <img src=\"https://img.shields.io/badge/java-%23ED8B00.svg?style=flat&logo=openjdk&logoColor=white\" alt=\"Java\">" +
				" <img src=\"https://img.shields.io/badge/Spring-6DB33F.svg?style=flat&logo=Spring&logoColor=white\" alt=\"Spring\">" +
				" <img src=\"https://img.shields.io/badge/Maven-C71A36.svg?style=flat&logo=Apache-Maven&logoColor=white\" alt=\"Maven\">" +
				" <img src=\"https://img.shields.io/badge/MongoDB-47A248.svg?style=flat&logo=MongoDB&logoColor=white\" alt=\"MongoDB\">" +
				" <img src=\"https://img.shields.io/badge/JSON-000000.svg?style=flat&logo=JSON&logoColor=white\" alt=\"JSON\">" +
				" <img src=\"https://img.shields.io/badge/Git-F05032.svg?style=flat&logo=Git&logoColor=white\" alt=\"Git\">" +
				" <img src=\"https://img.shields.io/badge/GitHub-181717.svg?style=flat&logo=GitHub&logoColor=white\" alt=\"GitHub\">" +
				" <img src=\"https://img.shields.io/badge/Postman-FF6C37.svg?style=flat&logo=Postman&logoColor=white\" alt=\"Postman\">" +
				" <img src=\"https://img.shields.io/badge/Apache-DA383E.svg?style=flat&logo=Apache&logoColor=white\" alt=\"Apache\">" +
				"</p>" +
				"<b>License</b><br>" +
				"<p>" +
				" Apache License 2.0 <img src=\"https://img.shields.io/badge/License-Apache%202.0-blue.svg?style=flat&logo=Apache&logoColor=white\" alt=\"Apache License 2.0\">" +
				"</p>";
		return meesage;
	}
}
