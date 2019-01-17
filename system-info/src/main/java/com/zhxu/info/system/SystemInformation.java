package com.zhxu.info.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SystemInformation {
    public static void main(String[] args) {
        SpringApplication.run(SystemInformation.class, args);
    }
}
