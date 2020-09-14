package com.testprojinc.dpcalc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class DpcalcApplication {

    public static void main(String[] args) {
        SpringApplication.run(DpcalcApplication.class, args);
    }

}
