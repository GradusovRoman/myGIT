package ru.geekbrains.thirdquarter.springintro.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.geekbrains.thirdquarter.springintro.springboot.app.ExtendedBCryptPasswordEncoder;

@SpringBootApplication
public class Application {

	@Bean
	public ExtendedBCryptPasswordEncoder getPasswordEncoder() {
		return new ExtendedBCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
