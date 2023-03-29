package com.unipd.semicolon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.unipd.semicolon")
public class SemicolonApplication {

	public static void main(String[] args) {
		SpringApplication.run(SemicolonApplication.class, args);
	}

}
