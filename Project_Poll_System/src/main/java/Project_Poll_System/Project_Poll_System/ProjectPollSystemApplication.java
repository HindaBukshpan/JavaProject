package Project_Poll_System.Project_Poll_System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableAutoConfiguration
@EnableFeignClients
public class ProjectPollSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectPollSystemApplication.class, args);
	}

}
