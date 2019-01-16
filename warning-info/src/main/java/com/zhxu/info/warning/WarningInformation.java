package com.zhxu.info.warning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WarningInformation {
    public static void main(String[] args) {
        SpringApplication.run(WarningInformation.class, args);
    }
}
