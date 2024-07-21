package org.dongguk.camputhon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class
CamputhonApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamputhonApplication.class, args);
	}

}
