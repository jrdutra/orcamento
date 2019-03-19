package com.fsma.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class ProjetoorcamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoorcamentoApplication.class, args);
	}

}
