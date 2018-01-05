package com.tpg.ocs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.tpg.ocs")
public class OcsWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(OcsWebApplication.class, args);
	}
}
