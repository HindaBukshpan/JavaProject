package Project_User_System.Project_User_System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableAutoConfiguration
@EnableFeignClients
public class ProjectUserSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectUserSystemApplication.class, args);
	}

}
