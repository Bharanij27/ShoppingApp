package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@EnableJpaRepositories("com.DAO")
@ServletComponentScan
@ComponentScan("com")
public class CodaSpringProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodaSpringProjectApplication.class, args);
	}

}
