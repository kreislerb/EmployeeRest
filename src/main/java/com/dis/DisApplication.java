package com.dis;

import com.dis.model.EmployeeEntity;
import com.dis.repository.EmployeeRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@SpringBootApplication
public class DisApplication extends SpringBootServletInitializer implements CommandLineRunner {
	@Autowired
	EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(DisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{

		int NUM_EMPLOYEERS = 200;
		List<EmployeeEntity> initialDataEmployeeList = new ArrayList<>();
		Faker faker = new Faker();

		for(int i = 0; i< NUM_EMPLOYEERS; i++){

			String firstname = faker.name().firstName();
			String surname = faker.name().lastName();
			String email = String.format("%s.%s@thalesgroup.com", firstname, surname).toLowerCase();
			EmployeeEntity e = EmployeeEntity.builder()
					.name(firstname)
					.surname(surname)
					.email(email)
					.gender(faker.bool().bool() ? 'M':'F')
					.ocupation(faker.company().profession())
					.status(faker.bool().bool())
					.salary(faker.number().randomDouble(2,4000,30000))
					.build();
			initialDataEmployeeList.add(e);
		}
		employeeRepository.deleteAll();
		employeeRepository.saveAll(initialDataEmployeeList);

	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:3000").allowedMethods("PUT", "DELETE",
						"GET", "POST");
			}
		};
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return super.configure(builder);
	}
}
