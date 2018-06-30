package com.hyt.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EwipServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EwipServerApplication.class, args);
    }
}
