package com.unipd.semicolon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.unipd.semicolon")
@EntityScan(basePackages = {"com.unipd.semicolon.core.entity"})
@EnableJpaRepositories(basePackages = "com.unipd.semicolon.core.repository")
@ServletComponentScan
public class SemicolonApplication {

    public static void main(String[] args) {
        SpringApplication.run(SemicolonApplication.class, args);
    }

}
