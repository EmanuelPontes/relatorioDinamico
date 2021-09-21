package com.empontes.relatorioDinamico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.empontes.relatorioDinamico.Repository")
@EntityScan("com.empontes.relatorioDinamico.Model")
@SpringBootApplication
public class RelatorioDinamicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RelatorioDinamicoApplication.class, args);
	}

}
