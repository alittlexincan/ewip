package com.zhxu.info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BasicInformation {
    public static void main(String[] args) {
        SpringApplication.run(BasicInformation.class, args);
    }
}
