package com.financialboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
@EnableJpaAuditing
public class FinancialBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancialBoardApplication.class, args);
	}
	
}
