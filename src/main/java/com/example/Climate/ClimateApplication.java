package com.example.Climate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class ClimateApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClimateApplication.class, args);
    }

}
